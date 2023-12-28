package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category extends com.example.demo.entity.Entity {

    private String name;
    
    @ManyToMany
    @JoinTable(name = "category_products",joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products;

    public void setName(String name) {
        this.name = name;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}