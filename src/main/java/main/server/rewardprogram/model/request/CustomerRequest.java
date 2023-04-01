package main.server.rewardprogram.model.request;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
public class CustomerRequest {
    private String name;
    private String surname;

    @Pattern(regexp = "\\d{3}-\\d{3}-\\d{4}")
    private String phoneNumber;
}
