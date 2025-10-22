package com.zexson.signor_p.UserModule;

import java.util.List;
import com.zexson.signor_p.DTO.UserCreateDTO;

public interface IUserService {

    User create(UserCreateDTO user);

    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    User findById(Long id);

    List<User> getAll();
}
