package com.zexson.signor_p.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zexson.signor_p.Models.User;
import com.zexson.signor_p.Services.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @PostMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> all() {
        return userService.all();
    }
}
