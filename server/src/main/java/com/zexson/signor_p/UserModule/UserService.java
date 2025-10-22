package com.zexson.signor_p.UserModule;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zexson.signor_p.DTO.UserCreateDTO;
import com.zexson.signor_p.untilities.TokenService;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepo;
    @Autowired
    TokenService tokenService;

    @Override
    public List<User> getAll() {
        return this.userRepo.findAll();
    }

    @Override
    public User create(UserCreateDTO userDTO) {
        User existsUser = this.userRepo.findByEmail(userDTO.getEmail());
        if (existsUser != null) {
            throw new IllegalArgumentException("Email must be unique");
        }
        User newUser = new User();
        newUser.setEmail(userDTO.getEmail());
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(userDTO.getPassword());
        newUser.setToken(tokenService.generateToken(30));
        this.userRepo.save(newUser);
        return existsUser;
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepo.findByEmail(email);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return this.userRepo.findByEmailAndPassword(email, password);
    }

    @Override
    public User findById(Long id) {
        return this.userRepo.findById(id).orElse(null);
    }
}
