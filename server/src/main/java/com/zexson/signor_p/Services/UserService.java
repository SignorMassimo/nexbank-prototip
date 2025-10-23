package com.zexson.signor_p.Services;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zexson.signor_p.DataObjectTransferClasses.DtoIUUser;
import com.zexson.signor_p.DataObjectTransferClasses.DtoUserResponse;
import com.zexson.signor_p.Impl.IUserService;
import com.zexson.signor_p.Models.User;
import com.zexson.signor_p.Repositories.UserRepository;
import com.zexson.signor_p.Security.JwtUtil;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public List<User> all() {
        return userRepo.findAll();
    }

    @Override
    public User create(DtoIUUser dtoUser) {
        User user = new User();
        BeanUtils.copyProperties(dtoUser, user);
        user.setRole("USER");
        userRepo.save(user);
        return user;
    }

    @Override
    public DtoUserResponse login(DtoIUUser dtoUser) {
        User user = userRepo.findByUsername(dtoUser.getUsername()).orElse(null);
        if (user == null || !user.getPassword().equals(dtoUser.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

        DtoUserResponse res = new DtoUserResponse();
        res.setUsername(user.getUsername());
        res.setToken(token);
        return res;
    }
}
