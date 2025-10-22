package com.zexson.signor_p.UserModule;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.zexson.signor_p.Base.BaseResponse;
import com.zexson.signor_p.DTO.UserByEmailDTO;
import com.zexson.signor_p.DTO.UserByIdDTO;
import com.zexson.signor_p.DTO.UserCreateDTO;
import com.zexson.signor_p.DTO.UserLoginDTO;
import com.zexson.signor_p.Response.CreateUserResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController implements IUserController {

    @Autowired
    UserService userService;
    @Autowired
    HttpServletResponse res;

    @PostMapping("/")
    @Override
    public List<User> users() {
        return this.userService.getAll();
    }

    @PostMapping("/create")
    @Override
    public BaseResponse create(@Valid @RequestBody UserCreateDTO user) {
        User newUser = this.userService.create(user);
        this.res.addCookie(new Cookie("token", newUser.getToken()));
        return new BaseResponse(new CreateUserResponse(newUser, newUser.getToken()), true, "User Created Successful");
    }

    @PostMapping("/by-email")
    @Override
    public BaseResponse byEmail(@Valid @RequestBody UserByEmailDTO user) {
        return new BaseResponse(this.userService.findByEmail(user.getEmail()), true, "");
    }

    @PostMapping("/by-id")
    @Override
    public BaseResponse byId(@Valid @RequestBody UserByIdDTO user) {
        return new BaseResponse(this.userService.userRepo.findById(user.getId()).orElse(null), true, "");
    }

    @PostMapping("/login")
    @Override
    public BaseResponse login(@Valid @RequestBody UserLoginDTO user) {
        User existsUser = this.userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (existsUser == null) {
            throw new NullPointerException("User not found");
        }
        res.addCookie(new Cookie("token", existsUser.getToken()));
        return new BaseResponse(new CreateUserResponse(existsUser, existsUser.getToken()), true, "Login Successful");
    }

    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse exceptionHandler(RuntimeException ex) {
        return new BaseResponse(null, false, ex.getMessage());
    }
}
