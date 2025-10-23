package com.zexson.signor_p.Transaction;

import com.zexson.signor_p.Base.BaseResponse;
import com.zexson.signor_p.DTO.ByUserDTO;
import com.zexson.signor_p.DTO.TransactionCreateDTO;

public interface ITransactionController {

    BaseResponse all();

    BaseResponse create(TransactionCreateDTO data);

    BaseResponse byUser(ByUserDTO data);
}
