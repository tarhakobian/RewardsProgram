package main.server.rewardprogram.controller;

import lombok.RequiredArgsConstructor;
import main.server.rewardprogram.model.request.CustomerRequest;
import main.server.rewardprogram.service.CustomerService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customers/{uuid}")
    public ResponseEntity<?> get(@PathVariable UUID uuid) {
        return ResponseEntity.ok(customerService.get(uuid));
    }

    @PostMapping("/customers")
    public ResponseEntity<?> create(@RequestBody CustomerRequest request) {
        return ResponseEntity.ok(customerService.create(request));
    }

    @PutMapping("/customer/{uuid}")
    public ResponseEntity<?> update(@PathVariable UUID uuid, @RequestBody CustomerRequest customerRequest) {
        customerService.update(uuid, customerRequest);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

}
