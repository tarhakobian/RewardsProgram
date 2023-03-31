package main.server.rewardprogram.controller;

import lombok.RequiredArgsConstructor;
import main.server.rewardprogram.service.RewardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class RewardsController {

    private final RewardService rewardService;

    @GetMapping("/rewards/{customerID}")
    public ResponseEntity<?> getPointsByID(@PathVariable UUID customerID) {
        return ResponseEntity.ok(rewardService.calculateEarnedPointsForPast3MonthsByID(customerID));

    }

    @GetMapping("/rewards")
    public ResponseEntity<?> getPoints() {
        return ResponseEntity.ok(rewardService.calculateEarnedPointsForPast3MonthsForAllCustomers());
    }

}
