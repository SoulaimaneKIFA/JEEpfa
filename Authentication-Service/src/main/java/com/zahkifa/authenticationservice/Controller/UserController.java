package com.zahkifa.authenticationservice.Controller;

import com.zahkifa.authenticationservice.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/users")
public class UserController {
    @Autowired
    AuthService authService;
    @GetMapping("/u")
    public String user(){
        return "je suis dans user controller";
    }
    @GetMapping("/me")
    public Object authenticatedUser() {
        return authService.getCurrentUser();
    }
}
