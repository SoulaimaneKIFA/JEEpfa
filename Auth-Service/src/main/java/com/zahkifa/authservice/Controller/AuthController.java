package com.zahkifa.authservice.Controller;

import com.zahkifa.authservice.Model.AuthRequest;
import com.zahkifa.authservice.Model.AuthResponse;
import com.zahkifa.authservice.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private final AuthService authService;

    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.register(authRequest));
    }
    @GetMapping("/a")
    public String a(){
        return "abdo";
    }
}
