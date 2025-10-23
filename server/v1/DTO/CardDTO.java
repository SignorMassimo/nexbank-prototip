package com.zexson.signor_p.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDTO {

    @NotNull
    @Positive
    private Long id;
    @NotBlank
    private String cardNumber;
    @NotBlank
    private String cardHolder;
    @NotNull
    @Positive
    private double balance;
}
