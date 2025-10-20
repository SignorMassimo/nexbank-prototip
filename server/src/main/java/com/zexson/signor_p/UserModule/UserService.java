package com.zexson.signor_p.UserModule;

import java.util.List;
import org.springframework.stereotype.Service;
import com.zexson.signor_p.helper_servers.TokenService;

@Service
public class UserService {
    UserRepository userRepo;
    TokenService tokenService;

    public UserService(UserRepository userRepo, TokenService tokenService) {
        this.userRepo = userRepo;
        this.tokenService = tokenService;
    }

    public List<User> getAll(){
        return this.userRepo.findAll();
    }

    public User create(User user) {
        User existsUser = this.userRepo.findByEmail(user.getEmail());
        if (existsUser != null)
            throw new IllegalArgumentException("Email must be unique");
        user.setToken(tokenService.generateToken(30));
        this.userRepo.save(user);
        return user;
    }

    public User findByEmail(String email) {
        return this.userRepo.findByEmail(email);
    }

    public User findByEmailAndPassword(String email,String password){
        return this.userRepo.findByEmailAndPassword(email, password);
    }
}
