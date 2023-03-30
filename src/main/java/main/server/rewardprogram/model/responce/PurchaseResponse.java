package main.server.rewardprogram.model.responce;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class PurchaseResponse {
    private Long id;
    private Long cost;
    private UUID customerID;
    private LocalDateTime createdAt;
}
