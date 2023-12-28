package com.example.demo.services;

import com.example.demo.entity.Material;
import com.example.demo.persistence.MaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public List<Material> findAll(){
        return materialRepository.findAll();
    }

    public Material findById(Long id){

        return materialRepository.findById(id).orElse(null);
    }
}
