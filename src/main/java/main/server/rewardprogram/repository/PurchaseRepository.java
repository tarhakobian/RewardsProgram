package main.server.rewardprogram.repository;

import main.server.rewardprogram.model.PurchaseEntity;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepository extends CrudRepository<PurchaseEntity, Long> {
}
