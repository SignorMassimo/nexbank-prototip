package com.zexson.signor_p.Response;

import com.zexson.signor_p.CardModule.Card;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCardResponse {
    private Card card;

    public CreateCardResponse(Card card){
        this.card = card;
    }
}
