package com.zexson.signor_p.Impl;

import java.util.List;
import com.zexson.signor_p.DataObjectTransferClasses.DtoIUUser;
import com.zexson.signor_p.DataObjectTransferClasses.DtoUserResponse;
import com.zexson.signor_p.Models.User;

public interface IUserService {

    public List<User> all();

    public User create(DtoIUUser dtoUser);

    public DtoUserResponse login(DtoIUUser dtoUser);
}
