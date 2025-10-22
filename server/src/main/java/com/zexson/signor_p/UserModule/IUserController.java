package com.zexson.signor_p.UserModule;

import java.util.List;
import com.zexson.signor_p.Base.BaseResponse;
import com.zexson.signor_p.DTO.UserByEmailDTO;
import com.zexson.signor_p.DTO.UserByIdDTO;
import com.zexson.signor_p.DTO.UserCreateDTO;
import com.zexson.signor_p.DTO.UserLoginDTO;

public interface IUserController {

    BaseResponse create(UserCreateDTO user);

    List<User> users();

    BaseResponse byEmail(UserByEmailDTO user);

    BaseResponse byId(UserByIdDTO user);

    BaseResponse login(UserLoginDTO user);
}
