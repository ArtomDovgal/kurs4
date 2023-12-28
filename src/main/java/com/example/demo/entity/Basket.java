package com.example.demo.entity;

import com.example.demo.entity.humans.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Entity
@Setter
public class Basket extends com.example.demo.entity.Entity {

    Integer totalSum;

    @OneToOne(mappedBy = "basket")
    User user;

    @OneToMany(mappedBy = "basket",fetch = FetchType.EAGER,orphanRemoval = true)
    List<OrderItem> orderItems;

}
