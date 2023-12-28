package com.example.demo.DTO.convertors;

import com.example.demo.DTO.BasketDTO;
import com.example.demo.entity.Basket;
import com.example.demo.entity.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class BasketDTOConvertor {


    public static BasketDTO convertToBasketDTO(Basket basket){
        List<Long> orderItemsId = new ArrayList<>();
        Integer totalSum = basket.getTotalSum();
        Long customerId = basket.getUser().getId();
        for (OrderItem orderItem: basket.getOrderItems()) {
            orderItemsId.add(orderItem.getId());
        }
        return new BasketDTO(totalSum,customerId,orderItemsId);
    }
}
