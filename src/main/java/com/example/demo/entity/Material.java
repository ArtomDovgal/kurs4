package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Material extends com.example.demo.entity.Entity {

    private String name;

    @ManyToMany
    @JoinTable(name = "material_product", joinColumns = @JoinColumn(name = "material_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
}
