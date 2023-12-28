package com.example.demo.services;

import com.example.demo.entity.OrderItem;
import com.example.demo.persistence.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;


    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public OrderItem save(OrderItem orderItem){

        return orderItemRepository.save(orderItem);
    }

    public OrderItem prepareToOrder(Long orderItemId){

        Optional<OrderItem> orderItemOptional = orderItemRepository.findById(orderItemId);

        orderItemOptional.ifPresent(orderItem -> orderItem.setBasket(null));

        return orderItemOptional.orElse(null);
    }
}
