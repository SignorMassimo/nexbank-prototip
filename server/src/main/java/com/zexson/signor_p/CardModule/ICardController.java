package com.zexson.signor_p.CardModule;

import com.zexson.signor_p.Base.BaseResponse;
import com.zexson.signor_p.DTO.ByIdDTO;
import com.zexson.signor_p.DTO.ByUserDTO;
import com.zexson.signor_p.DTO.CardBalanceDTO;
import com.zexson.signor_p.DTO.CardByNumberDTO;
import com.zexson.signor_p.DTO.CardCreateDTO;

public interface ICardController {

    public BaseResponse byUser(ByUserDTO card);

    public BaseResponse create(CardCreateDTO card);

    public BaseResponse byId(ByIdDTO card);

    public BaseResponse balance(CardBalanceDTO card);

    public BaseResponse byNumber(CardByNumberDTO card);

    public BaseResponse delete(ByIdDTO card);
}
