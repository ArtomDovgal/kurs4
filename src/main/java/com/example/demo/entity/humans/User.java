package com.example.demo.entity.humans;

import com.example.demo.entity.Basket;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.entity.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "users")
public class User extends com.example.demo.entity.Entity {

    private String email;
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    private String phone;
    private String address;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    @ManyToMany
    @JoinTable(name = "product_wishlistsuserscustomers",
            joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> favoritesProducts;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "basket_id",nullable = false)
    private Basket basket;

}
