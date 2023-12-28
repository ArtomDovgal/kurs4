package com.example.demo.DTO;

import com.example.demo.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDTO extends EntityDTO{

    private Long orderId;
    private Long productId;
    private String productName;
    private Double price;
    private Integer quantity;
    private Long basketId;

    public OrderItemDTO(OrderItem orderItem){
        this.id = orderItem.getId();
        this.basketId = orderItem.getBasket().getId();
        if(orderItem.getOrder() != null) this.orderId = orderItem.getOrder().getId();
        this.productId =orderItem.getProduct().getId();
        this.productName = orderItem.getProduct().getName();
        this.price = orderItem.getProduct().getPrice();
        this.quantity = orderItem.getQuantity();

    }
}
