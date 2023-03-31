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

    public static void main(String[] args) throws SQLException {
        ConfigurableApplicationContext context = SpringApplication.run(RewardProgramApplication.class, args);
        RewardService service = context.getBean(RewardService.class);
        System.out.println(service.calculateEarnedPointsForPast3MonthsByID(UUID.fromString("11111111-1111-1111-1111-111111111111")));
    }

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
