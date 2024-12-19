package com.zahkifa.authenticationservice.Controller;

import com.zahkifa.authenticationservice.Configs.LoginResponse;
import com.zahkifa.authenticationservice.DTO.UserLoginRequest;
import com.zahkifa.authenticationservice.DTO.UserSignupRequest;
import com.zahkifa.authenticationservice.Entities.User;
import com.zahkifa.authenticationservice.Service.AuthService;
import com.zahkifa.authenticationservice.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    JwtService jwtService;
    @Autowired
     AuthService authenticationService;
    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody UserSignupRequest user) {
        User registeredUser=authenticationService.signup(user);
        return ResponseEntity.ok(registeredUser);
    }
    @GetMapping("/a")
    public String hello(@RequestHeader("Authorization") String authorizationHeader){
        if(authorizationHeader!=null){
            return "hello";
        }
        return "null";
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserLoginRequest user) {
        try{
            User authenticatedUser=authenticationService.login(user);
            String jwtToken = jwtService.generateToken(authenticatedUser);
            LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());
            return ResponseEntity.ok(loginResponse);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    @GetMapping("/me")
    public ResponseEntity<Boolean> me(){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Boolean currentUser = authentication.isAuthenticated();
            return ResponseEntity.ok(currentUser);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authorizationHeader) {
        System.out.println(authorizationHeader);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Extraire le token
            System.out.println(token);
            jwtService.blacklistToken(token); // Ajouter le token à la liste noire
            return ResponseEntity.ok("Logout successful");
        } else {
            return ResponseEntity.status(401).body("Unauthorized");
        }
    }
}
