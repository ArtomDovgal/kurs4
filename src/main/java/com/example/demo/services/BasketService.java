package com.example.demo.services;

import com.example.demo.entity.Basket;
import com.example.demo.entity.OrderItem;
import com.example.demo.persistence.BasketRepository;
import com.example.demo.persistence.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BasketService {

    private final BasketRepository basketRepository;
    private final OrderItemRepository orderItemRepository;

    public BasketService(BasketRepository basketRepository, OrderItemRepository orderItemRepository) {
        this.basketRepository = basketRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public Basket findById(Long id){

        return basketRepository.findById(id).orElse(null);
    }

    public Basket save(Basket basket){

        return basketRepository.save(basket);
    }

    public void clearById(Long id){

        Optional<Basket> basketOptional = basketRepository.findById(id);

        if(basketOptional.isPresent()){
            Basket basket = basketOptional.get();
            basket.getOrderItems().clear();
            basketRepository.save(basket);
        }

    }

    public void addOrderItem(OrderItem orderItem,Long id){

        Optional<Basket> basketOptional = basketRepository.findById(id);

        if(basketOptional.isPresent()){
            Basket basket = basketOptional.get();
            basket.getOrderItems().add(orderItem);
            basketRepository.save(basket);
        }

    }

    public void removeOrderItem(Long orderItemId,Long id) {

        Optional<OrderItem> orderItemOptional = orderItemRepository.findById(orderItemId);
        if (orderItemOptional.isPresent()) {

            Optional<Basket> basketOptional = basketRepository.findById(id);

            if (basketOptional.isPresent()) {

                Basket basket = basketOptional.get();
                basket.getOrderItems().remove(orderItemOptional.get());
                basketRepository.save(basket);
                orderItemRepository.delete(orderItemOptional.get());
            }

        }
    }

}
