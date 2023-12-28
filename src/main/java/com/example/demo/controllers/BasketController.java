package com.example.demo.controllers;

import com.example.demo.DTO.BasketDTO;
import com.example.demo.DTO.OrderItemDTO;
import com.example.demo.DTO.convertors.BasketDTOConvertor;
import com.example.demo.entity.Basket;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Product;
import com.example.demo.services.BasketService;
import com.example.demo.services.OrderItemService;
import com.example.demo.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BasketController {

    private final BasketService basketService;
    private final ProductService productService;
    private final OrderItemService orderItemService;

    public BasketController(BasketService basketService, ProductService productService, OrderItemService orderItemService) {
        this.basketService = basketService;
        this.productService = productService;
        this.orderItemService = orderItemService;
    }

    @GetMapping("basket/{id}")
    public String getBasketById(@PathVariable String id, Model model){
        Basket basket = basketService.findById(Long.parseLong(id));
        List<OrderItemDTO> orderItemDTOList = new ArrayList<>();
        for(OrderItem orderItem : basket.getOrderItems()){
            orderItemDTOList.add(new OrderItemDTO(orderItem));
        }
        BasketDTO basketDTO = BasketDTOConvertor.convertToBasketDTO(basket);
        model.addAttribute("basket", basketDTO);
        model.addAttribute("orderItems",orderItemDTOList);

        return "main/basket";
    }

    @PostMapping("/basket/add")
    public String addItem(@RequestParam("basketId") String basketID, @RequestParam("productId") String productID){
        Long basketId = Long.parseLong(basketID);
        Long productId = Long.parseLong(productID);
        Product product = productService.findById(productId);
        Basket basket = basketService.findById(basketId);
        OrderItem orderItem = new OrderItem();
        orderItem.setBasket(basket);
        orderItem.setProduct(product);
        orderItem.setQuantity(1);
        orderItemService.save(orderItem);
        basket.getOrderItems().add(orderItem);
        basketService.save(basket);

        return "redirect:/products";
    }
}
