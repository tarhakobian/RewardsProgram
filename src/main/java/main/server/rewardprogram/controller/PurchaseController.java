package main.server.rewardprogram.controller;

import lombok.RequiredArgsConstructor;
import main.server.rewardprogram.model.request.PurchaseRequest;
import main.server.rewardprogram.model.responce.PurchaseResponse;
import main.server.rewardprogram.service.PurchaseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping("/purchases")
    public PurchaseResponse get(@RequestParam Long id) {
        return purchaseService.get(id);
    }

    @PostMapping("/purchases")
    public Long create(@RequestBody PurchaseRequest request) {
        return purchaseService.create(request);
    }
}
