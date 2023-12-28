package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Brand extends com.example.demo.entity.Entity {

    private String name;
    
    @OneToMany(mappedBy = "brand")
    private List<Product> products = new ArrayList<>();
}