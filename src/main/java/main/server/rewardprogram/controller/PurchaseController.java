package main.server.rewardprogram.controller;

import lombok.RequiredArgsConstructor;
import main.server.rewardprogram.model.request.PurchaseRequest;
import main.server.rewardprogram.service.PurchaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping("/purchases/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseService.get(id));
    }

    @PostMapping("/purchases")
    public ResponseEntity<?> create(@RequestBody PurchaseRequest request) {
        return ResponseEntity.ok(purchaseService.create(request));
    }

}
