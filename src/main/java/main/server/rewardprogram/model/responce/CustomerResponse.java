package main.server.rewardprogram.model.responce;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CustomerResponse {
    private UUID uuid;
    private String name;
    private String surname;
    private String phoneNumber;
    private Integer numberOfPurchases;
}
