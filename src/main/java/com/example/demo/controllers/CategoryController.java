package com.example.demo.controllers;

import com.example.demo.entity.Category;
import com.example.demo.persistence.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CategoryController {

    private CategoryRepository categoryRepository;

    @GetMapping("categories")
    public List<Category> getCategories(){

        return categoryRepository.findAll();
    }
}
