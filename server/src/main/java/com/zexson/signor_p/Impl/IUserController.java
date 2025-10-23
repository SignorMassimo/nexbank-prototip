package com.zexson.signor_p.Impl;

import java.util.List;
import com.zexson.signor_p.DataObjectTransferClasses.DtoIUUser;
import com.zexson.signor_p.DataObjectTransferClasses.DtoUserResponse;
import com.zexson.signor_p.Models.User;
import jakarta.servlet.http.HttpServletRequest;

public interface IUserController {

    public DtoUserResponse register(DtoIUUser dtoIUUser);

    public DtoUserResponse login(DtoIUUser dtoIUUser);

    public String me(HttpServletRequest req);

    public List<User> all();
}
