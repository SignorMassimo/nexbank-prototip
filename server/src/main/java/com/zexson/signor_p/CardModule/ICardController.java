package com.zexson.signor_p.CardModule;

import com.zexson.signor_p.Base.BaseResponse;
import com.zexson.signor_p.DTO.CardByUserDTO;
import com.zexson.signor_p.DTO.CardCreateDTO;

public interface ICardController {

    public BaseResponse byUser(CardByUserDTO card);

    public BaseResponse create(CardCreateDTO card);

    public BaseResponse byId(Card card);

    public BaseResponse balance(Card card, Long cardId);

    public BaseResponse byNumber(Card card);

    public BaseResponse delete(Card card);
}
