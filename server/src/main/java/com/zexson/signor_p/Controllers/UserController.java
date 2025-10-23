package com.zexson.signor_p.Controllers;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zexson.signor_p.DataObjectTransferClasses.DtoIUUser;
import com.zexson.signor_p.DataObjectTransferClasses.DtoUserResponse;
import com.zexson.signor_p.Impl.IUserController;
import com.zexson.signor_p.Models.User;
import com.zexson.signor_p.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController implements IUserController {

    @Autowired
    UserService userService;

    @Override
    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> all() {
        return userService.all();
    }

    @Override
    @PostMapping("/register")
    public DtoUserResponse register(@Valid @RequestBody DtoIUUser dtoUser) {
        DtoUserResponse newDtoUser = new DtoUserResponse();
        User user = userService.create(dtoUser);
        BeanUtils.copyProperties(user, newDtoUser);
        return newDtoUser;
    }

    @Override
    @PostMapping("/login")
    public DtoUserResponse login(@RequestBody DtoIUUser dtouser) {
        return userService.login(dtouser);
    }

    @Override
    @PostMapping("/me")
    public String me(HttpServletRequest req) {
        return "Hello, " + req.getAttribute("username");
    }

}
