package com.zahkifa.authenticationservice.Service;

import com.zahkifa.authenticationservice.DTO.UserLoginRequest;
import com.zahkifa.authenticationservice.DTO.UserSignupRequest;
import com.zahkifa.authenticationservice.Entities.Roles;
import com.zahkifa.authenticationservice.Entities.User;
import com.zahkifa.authenticationservice.Repo.RoleRepo;
import com.zahkifa.authenticationservice.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RoleRepo roleRepo;
    public User signup(UserSignupRequest userSignupRequest) {
        User user = new User(userSignupRequest.fullName, userSignupRequest.email, passwordEncoder.encode(userSignupRequest.password));
        Optional<Roles> userRole=roleRepo.findById(1);
        user.addRole(userRole.get());
        return userRepo.save(user);
    }
    public User login(UserLoginRequest userLoginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginRequest.email,
                        userLoginRequest.password
                )
        );
        return userRepo.findByEmail(userLoginRequest.email)
                .orElseThrow();
    }
    public Optional<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepo.findByEmail(authentication.getName());
    }
}
