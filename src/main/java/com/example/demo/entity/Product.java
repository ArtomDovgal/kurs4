package com.example.demo.entity;

import com.example.demo.entity.humans.User;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
public class Product extends com.example.demo.entity.Entity {

    private String name;

    private String description;

    private Double price;

    private Integer quantity;

    private Integer height;

    private Integer length;

    private Integer width;

    @Lob
    private byte[] image;

    @ManyToMany(mappedBy = "products", cascade = CascadeType.ALL)
    List<Material> materials;

    @ManyToMany(mappedBy = "products", fetch = FetchType.EAGER)
    private List<Category> categories;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @ManyToMany(mappedBy = "favoritesProducts", fetch = FetchType.EAGER)
    private List<User> wishlistsCustomers;

    public Product(Brand brand){
        super();
        this.brand = brand;
        this.materials = new ArrayList<>();
        this.categories = new ArrayList<>();
    }

    public Product(Long id,String name, String description, Double price, Integer quantity, Integer height, Integer length, Integer width, byte[] image,
                   List<Material> materials, List<Category> categories, Brand brand,
                   List<User> wishlistsCustomers) {
        super(id);
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.height = height;
        this.length = length;
        this.width = width;
        this.image = image;
        this.materials = materials;
        this.categories = categories;
        this.brand = brand;
        this.wishlistsCustomers = wishlistsCustomers;
    }
}
