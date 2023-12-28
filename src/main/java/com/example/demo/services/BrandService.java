package com.example.demo.services;

import com.example.demo.entity.Brand;
import com.example.demo.persistence.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    private final BrandRepository brandRepository;


    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<Brand> findAll(){

        return brandRepository.findAll();
    }

    public Brand findById(Long id){
        Optional<Brand> brandOptional = brandRepository.findById(id);

        return brandOptional.orElse(null);
    }

    public Brand save(Brand brand){

        return brandRepository.save(brand);
    }
}
