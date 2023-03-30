package main.server.rewardprogram.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PurchaseRequest {
    private UUID customerID;
    private Long cost;
}
