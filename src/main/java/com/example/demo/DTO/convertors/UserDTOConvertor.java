package com.example.demo.DTO.convertors;

import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.entity.humans.User;

import java.util.ArrayList;
import java.util.List;

public class UserDTOConvertor {

    public UserDTO convertToUserDTO(User user){
        List<Long> favoriteListId = new ArrayList<>();
        List<Long> orderListId = new ArrayList<>();
        UserDTO userDTO = new UserDTO();

        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setAddress(user.getAddress());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(userDTO.getPhone());
        userDTO.setBasketId(user.getBasket().getId());

        for(Order order : user.getOrders()){
            orderListId.add(order.getId());
        }
        for(Product product : user.getFavoritesProducts()){
            favoriteListId.add(product.getId());
        }

        userDTO.setOrdersId(orderListId);
        userDTO.setFavoritesProductsId(favoriteListId);
        userDTO.setRoleId(user.getRole().getId());

        return userDTO;
    }
}
