package com.zexson.signor_p.UserModule;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.zexson.signor_p.Base.BaseResponse;
import com.zexson.signor_p.Response.CreateUserResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;
    HttpServletResponse res;

    public UserController(UserService userService, HttpServletResponse res) {
        this.userService = userService;
        this.res = res;
    }

    @PostMapping("/")
    public List<User> postMethodName() {
        return this.userService.getAll();
    }

    @PostMapping("/create")
    public BaseResponse create(@RequestBody User user) {
        User newUser = this.userService.create(user);
        this.res.addCookie(new Cookie("token", newUser.getToken()));
        return new BaseResponse(new CreateUserResponse(newUser, newUser.getToken()), true, "User Created Successful");
    }

    @PostMapping("/by-email")
    public BaseResponse byEmail(@RequestBody User user) {
        return new BaseResponse(this.userService.findByEmail(user.getEmail()), true, "");
    }

    @PostMapping("/by-id")
    public BaseResponse byId(@RequestBody User user) {
        return new BaseResponse(this.userService.userRepo.findById(user.getId()).orElse(null), true, "");
    }

    @PostMapping("/login")
    public BaseResponse postMethodName(@RequestBody User user) {
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
