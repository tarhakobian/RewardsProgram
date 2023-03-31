package main.server.rewardprogram.service;

import lombok.RequiredArgsConstructor;
import main.server.rewardprogram.exception.CustomerNotFoundException;
import main.server.rewardprogram.exception.PurchaseNotFoundException;
import main.server.rewardprogram.model.entity.PurchaseEntity;
import main.server.rewardprogram.model.request.PurchaseRequest;
import main.server.rewardprogram.model.responce.PurchaseResponse;
import main.server.rewardprogram.repository.CustomerRepository;
import main.server.rewardprogram.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final CustomerRepository customerRepository;

    public Long create(PurchaseRequest request) {
        PurchaseEntity entity = new PurchaseEntity();

        entity.setCustomerEntity(customerRepository.findById(request.getCustomerID()).
                orElseThrow(CustomerNotFoundException::new));
        entity.setCost(request.getCost());

        purchaseRepository.save(entity);

        return entity.getId();
    }


    public PurchaseResponse get(Long id) {
        PurchaseEntity entity = purchaseRepository.findById(id).
                orElseThrow(PurchaseNotFoundException::new);

        PurchaseResponse response = new PurchaseResponse();

        response.setId(entity.getId());
        response.setCost(entity.getCost());
        response.setCustomerID(entity.getCustomerEntity().getId());
        response.setCreatedAt(entity.getCreatedAt());

        return response;
    }

    public List<PurchaseResponse> getAll() {
        List<PurchaseEntity> entities = StreamSupport.stream(purchaseRepository.findAll().spliterator(), false)
                .toList();

        System.out.print(entities);

        return entities.stream().
                map(entity -> {
                    PurchaseResponse purchaseResponse = new PurchaseResponse();

                    purchaseResponse.setId(entity.getId());
                    purchaseResponse.setCost(entity.getCost());
                    purchaseResponse.setCreatedAt(entity.getCreatedAt());
                    purchaseResponse.setCustomerID(entity.getCustomerEntity().getId());

                    return purchaseResponse;
                }).collect(Collectors.toList());
    }
}
