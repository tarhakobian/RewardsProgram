package main.server.rewardprogram.service;

import lombok.RequiredArgsConstructor;
import main.server.rewardprogram.exception.CustomerNotFoundException;
import main.server.rewardprogram.model.entity.CustomerEntity;
import main.server.rewardprogram.model.request.CustomerRequest;
import main.server.rewardprogram.model.responce.CustomerResponse;
import main.server.rewardprogram.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;


    public CustomerResponse get(UUID uuid) {
        CustomerEntity entity = customerRepository.findById(uuid).orElseThrow(CustomerNotFoundException::new);

        CustomerResponse response = new CustomerResponse();

        response.setName(entity.getName());
        response.setSurname(entity.getSurname());
        response.setPhoneNumber(entity.getPhoneNumber());
        response.setNumberOfPurchases(entity.getPurchases().size());

        return response;
    }

    public UUID create(CustomerRequest request) {
        CustomerEntity entity = new CustomerEntity();

        entity.setId(UUID.randomUUID());
        entity.setName(request.getName());
        entity.setSurname(request.getSurname());
        entity.setPhoneNumber(request.getPhoneNumber());

        customerRepository.save(entity);

        return entity.getId();
    }

    public boolean update(UUID uuid, CustomerRequest customerRequest) {
        CustomerEntity entity = customerRepository.findById(uuid).
                orElseThrow(CustomerNotFoundException::new);

        entity.setName(customerRequest.getName());
        entity.setSurname(customerRequest.getSurname());
        entity.setPhoneNumber(customerRequest.getPhoneNumber());

        customerRepository.save(entity);

        return true;
    }
}
