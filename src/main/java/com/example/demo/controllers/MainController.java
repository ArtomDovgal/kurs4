package com.example.demo.controllers;

import com.example.demo.entity.Role;
import com.example.demo.entity.humans.User;
import com.example.demo.persistence.RoleRepository;
import com.example.demo.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    private final UserService userService;
    private final RoleRepository roleRepository;

    public MainController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/")
    public String main(){
        return "redirect:/products";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("user",new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User user,Model model){
        Role role = roleRepository.findByTypeRole(Role.TypeRole.CUSTOMER).get();
        userService.save(user,role);
        return "redirect:/login";
    }


}
