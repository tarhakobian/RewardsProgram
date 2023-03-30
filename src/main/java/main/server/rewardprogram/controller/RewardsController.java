package main.server.rewardprogram.controller;

import lombok.RequiredArgsConstructor;
import main.server.rewardprogram.service.RewardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class RewardsController {

    private final RewardService rewardService;

    @GetMapping("/rewards")
    public Map<String, Integer> getPointCount(@RequestParam UUID customerID) throws SQLException {
        return rewardService.calculateEarnedPointsForPast3Months(customerID);
    }

}
