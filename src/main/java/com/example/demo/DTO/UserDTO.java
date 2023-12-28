package com.example.demo.DTO;

import com.example.demo.entity.Basket;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.entity.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {

    private String email;
    private Long roleId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private List<Long> ordersId;
    private List<Long> favoritesProductsId;
    private Long basketId;
}
