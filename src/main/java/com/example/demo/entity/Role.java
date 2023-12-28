package com.example.demo.entity;

import com.example.demo.entity.humans.User;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;

import java.util.Set;

@Entity
@Getter
@Table(name = "roles")
public class Role extends com.example.demo.entity.Entity {

    @Enumerated(EnumType.STRING)
    private TypeRole typeRole;

    @OneToMany(mappedBy = "role")
    private Set<User> users;

    public static enum TypeRole{
        CUSTOMER,ADMIN,DIRECTOR,WORKER
    }


}
