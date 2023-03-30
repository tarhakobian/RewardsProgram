package main.server.rewardprogram.repository;

import main.server.rewardprogram.model.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CustomerRepository extends CrudRepository<CustomerEntity, UUID> {
}
