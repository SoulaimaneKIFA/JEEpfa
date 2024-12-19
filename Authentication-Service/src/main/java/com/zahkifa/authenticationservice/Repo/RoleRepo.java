package com.zahkifa.authenticationservice.Repo;

import com.zahkifa.authenticationservice.Entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepo extends JpaRepository<Roles, Integer> {
}
