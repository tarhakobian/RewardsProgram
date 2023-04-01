package main.server.rewardprogram.model.responce;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponse {
    private String name;
    private String surname;
    private String phoneNumber;
    private Integer numberOfPurchases;
}
