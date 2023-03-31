package main.server.rewardprogram.service;

import lombok.RequiredArgsConstructor;
import main.server.rewardprogram.RewardProgramApplication;
import main.server.rewardprogram.model.responce.PurchaseResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RewardService {

    private final JdbcTemplate jdbcTemplate;
    private final PurchaseService purchaseService;

    /** Short demonstration that calculateEarnedPointsForPast3MonthsByID(UUID uuid) works properly */
    public static void main(String[] args) throws SQLException {
        ConfigurableApplicationContext context = SpringApplication.run(RewardProgramApplication.class, args);
        RewardService service = context.getBean(RewardService.class);
        System.out.println(service.calculateEarnedPointsForPast3MonthsByID(UUID.fromString("11111111-1111-1111-1111-111111111111")));
    }

    /** This function takes a UUID as input and calculates the total reward
     *  points earned by that customer in the past 3 months, separated by month.
     *  It does this by executing a SQL query using the JDBC template to sum the
     *  reward points earned by the customer for each month. The results are then
     *  stored in a Map with the month label as the key and the total reward points as the value.
     */
    public Map<String, Integer> calculateEarnedPointsForPast3MonthsByID(UUID uuid) {

        Map<String, Integer> pointsMap = new HashMap<>();

        for (int i = 0; i < 3; i++) {

            String sql = "SELECT SUM(calculate_rewards_points(p.cost)) AS total_points " +
                    "FROM purchase p " +
                    "WHERE p.customer_id = ? and p.created_at >= DATE_TRUNC('month', NOW() - INTERVAL '" + i + " months')" +
                    " and  p.created_at <= DATE_TRUNC('month', NOW() - INTERVAL '" + (i - 1) + " months') - INTERVAL '1 day'";

            Integer pointsForThisMonth = jdbcTemplate.
                    queryForObject(sql, Integer.class, uuid);

            if (i == 0) {
                pointsMap.put("current moth", pointsForThisMonth);
            } else {
                pointsMap.put(i + " moths ago", pointsForThisMonth);
            }
        }

        return pointsMap;
    }

    /** This function calculates the total earned points for each customer for the past 3 months.
     *  It does this by iterating over all purchases, and for each purchase,
     *  it either creates a new entry in the result map for the customer and calculates
     *  the earned points for the past 3 months, or merges the existing entry with the
     *  calculated earned points for the purchase.*/
    public Map<UUID, Map<String, Integer>> calculateEarnedPointsForPast3MonthsForAllCustomers() {
        List<PurchaseResponse> purchases = purchaseService.getAll();

        Map<UUID, Map<String, Integer>> result = new HashMap<>();

        purchases.forEach(purchase -> {
            if (!result.containsKey(purchase.getCustomerID())) {
                result.put(purchase.getCustomerID(), calculateEarnedPointsForPast3MonthsByID(purchase.getCustomerID()));
            } else {
                result.merge(purchase.getCustomerID(), calculateEarnedPointsForPast3MonthsByID(purchase.getCustomerID()),
                        (map1, map2) -> {
                            map1.forEach((key, value) -> {
                                map1.merge(key, map2.get(key), Integer::sum);
                            });
                            return map1;
                        });
            }
        });

        return result;
    }

}
