package com.zahkifa.authenticationservice.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Roles {
    @Id
    private Integer id;
    private String role;
    @ManyToMany(mappedBy="roles",fetch = FetchType.EAGER)
    private List<User> users=new ArrayList<>();
    public Roles(Integer id,String role) {
        this.id = id;
        this.role = role;
    }
}
