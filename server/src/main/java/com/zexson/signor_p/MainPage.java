package com.zexson.signor_p;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainPage {

    @GetMapping("/")
    public String main() {
        return "Main Page";
    }

}
