package com.example.demo.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.humans.User;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Data
@Table(name = "orders")
public class Order extends com.example.demo.entity.Entity {

@ManyToOne
@JoinColumn(name = "user_id")
private User user;

private String shippingAddress;

private LocalDate orderDate;

@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
private List<OrderItem> products = new ArrayList<>();

private Float totalPrice;

}
