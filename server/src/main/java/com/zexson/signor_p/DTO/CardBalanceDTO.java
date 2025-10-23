package com.zexson.signor_p.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardBalanceDTO {

    @NotNull
    @Positive
    private Long cardId;
    @NotNull
    private Double balance;
}
