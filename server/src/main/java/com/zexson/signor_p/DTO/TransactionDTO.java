package com.zexson.signor_p.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDTO {

    @NotNull
    @Positive
    private Long id;
    @NotNull
    @Positive
    private Long fromCardId;
    @NotNull
    @Positive
    private Long fromUserId;
    @NotNull
    @Positive
    private Long toCardId;
    @NotNull
    @Positive
    private Long toUserId;
}
