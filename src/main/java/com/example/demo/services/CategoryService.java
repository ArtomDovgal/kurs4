package com.example.demo.services;

import com.example.demo.entity.Category;
import com.example.demo.persistence.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll(){

        return categoryRepository.findAll();
    }

    public Category add(Category category){
       return categoryRepository.save(category);
    }

    public Category update(Category category,Long id){
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        if(categoryOptional.isPresent()){
            Category categoryToUpdate = categoryOptional.get();
            categoryToUpdate.setName(category.getName());
            return categoryRepository.save(categoryToUpdate);
        }
        return null;
    }

    public Category findById(Long id){
        return categoryRepository.findById(id).orElse(null);
    }

}
