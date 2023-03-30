package main.server.rewardprogram.service;

import main.server.rewardprogram.RewardProgramApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class RewardService {

    private final JdbcTemplate jdbcTemplate;

    public RewardService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer calculateRewardsPoints(Integer purchaseAmount) throws SQLException {
        String sql = "SELECT calculate_rewards_points(?)";
        return jdbcTemplate.queryForObject(sql, Integer.class, purchaseAmount);
    }


    public Map<String, Integer> calculateEarnedPointsForPast3Months(UUID uuid) {

        Map<String, Integer> pointsMap = new HashMap<>();

        for (int i = 0; i < 3; i++) {

            String sql = "SELECT SUM(calculate_rewards_points(p.cost)) AS total_points " +
                    "FROM purchase p " +
                    "WHERE p.customer_id = ? and p.created_at >= DATE_TRUNC('month', NOW() - INTERVAL '" + i + " months')" +
                    " and  p.created_at <= DATE_TRUNC('month', NOW() - INTERVAL '" + (i-1) + " months') - INTERVAL '1 day'";

            Integer pointsForThisMonth = jdbcTemplate.
                    queryForObject(sql, Integer.class, uuid);

            pointsMap.put(String.valueOf(i), pointsForThisMonth);
        }

        return pointsMap;
    }


    public static void main(String[] args) throws SQLException {
        ConfigurableApplicationContext context = SpringApplication.run(RewardProgramApplication.class, args);
        RewardService service = context.getBean(RewardService.class);
        System.out.println(service.calculateEarnedPointsForPast3Months(UUID.fromString("11111111-1111-1111-1111-111111111111")));
    }
}
