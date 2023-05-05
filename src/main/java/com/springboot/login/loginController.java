package com.springboot.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
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

    @GetMapping("/secured1")
    @Secured({"ROLE_secure1"})
    public String secured1() {
        return "secured1";
    }

    @GetMapping("/preAuthorize1")
    @PreAuthorize("hasAnyAuthority('manager')") // 方法之前驗證
    public String preAuthorize1() {
        return "preAuthorize1";
    }

    @GetMapping("/preAuthorize2")
    @PostAuthorize("hasAnyAuthority('managers')") // 方法之後驗證
    public String preAuthorize2() {
        System.out.println("preAuthorize2");
        return "preAuthorize2";
    }

    @GetMapping("/successLogout")
    public String successLogout() {
        return "successLogout";
    }


// http://localhost:8081/login/preAuthorize2
}
