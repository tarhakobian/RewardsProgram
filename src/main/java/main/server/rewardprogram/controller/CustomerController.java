package main.server.rewardprogram.controller;

import lombok.RequiredArgsConstructor;
import main.server.rewardprogram.model.request.CustomerRequest;
import main.server.rewardprogram.model.responce.CustomerResponse;
import main.server.rewardprogram.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customers")
    public CustomerResponse get(@RequestParam UUID uuid) {
        return customerService.get(uuid);
    }

    @PostMapping("/customers")
    public UUID create(@RequestBody CustomerRequest request) {
        return customerService.create(request);
    }

    @PutMapping("/customer")
    public void update(@RequestParam UUID uuid,
                       @RequestParam CustomerRequest customerRequest) {
        customerService.update(uuid, customerRequest);
    }
}
