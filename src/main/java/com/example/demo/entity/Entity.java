package com.example.demo.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;


@Getter
@MappedSuperclass
public abstract class Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Entity(Long id) {
        this.id = id;
    }
    public Entity(){}

    public void setId(Long id) {
        this.id = id;
    }
}
