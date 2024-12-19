package com.zahkifa.authenticationservice;

import com.zahkifa.authenticationservice.Entities.Roles;
import com.zahkifa.authenticationservice.Entities.User;
import com.zahkifa.authenticationservice.Repo.RoleRepo;
import com.zahkifa.authenticationservice.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        Roles userRole = new Roles(1,"user");
        roleRepo.save(userRole);
        Roles adminRole = new Roles(2,"admin");
        roleRepo.save(adminRole);
        User user=new User("ZahKifa","ZahKifa@gmail.com",passwordEncoder.encode("ZahKifa"));
        user.addRole(userRole);
        userRepo.save(user);
    }
}
