package com.example.demo.controllers;

import com.example.demo.services.BasketService;
import com.example.demo.services.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    private final IUserService userService;
    private final BasketService basketService;

    public UserController(IUserService IUserService, BasketService basketService) {
        this.userService = IUserService;
        this.basketService = basketService;
    }

    @GetMapping("customer/{id}")
    public void getCustomerById(@PathVariable("id") String id, Model model){
        model.addAttribute("customer", userService.findById(Long.parseLong(id)));
    }
}
