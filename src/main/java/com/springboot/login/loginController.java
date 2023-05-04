package com.springboot.login;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class loginController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }

}
