package com.example.demo.services;

import com.example.demo.entity.Order;
import com.example.demo.entity.humans.User;
import com.example.demo.persistence.UserRepository;
import com.example.demo.persistence.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }



    public List<Order> getAll(){
        return orderRepository.findAll();
    }
    public Order save(Order order){

        return orderRepository.save(order);
    }

    public Order findById(Long id){
        return orderRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id){

        Optional<Order> orderOptional = orderRepository.findById(id);

        if(orderOptional.isPresent()) {
            User user = orderOptional.get().getUser();
            user.getOrders().remove(orderOptional.get());
            userRepository.save(user);

            orderRepository.deleteById(orderOptional.get().getId());
        }
    }

    public Order update(Order order){

        Optional<Order> orderOptional = orderRepository.findById(order.getId());

        if(orderOptional.isPresent()){
            Order orderToUpdate = orderOptional.get();

            orderToUpdate.setOrderDate(order.getOrderDate());
            orderToUpdate.setUser(order.getUser());
            orderToUpdate.setTotalPrice(order.getTotalPrice());
            orderToUpdate.setShippingAddress(order.getShippingAddress());

            return orderRepository.save(orderToUpdate);
        }
        return null;
    }


}
