package com.example.demo.DTO.convertors;

import com.example.demo.DTO.BrandDTO;
import com.example.demo.entity.Brand;
import com.example.demo.entity.Product;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;

public class BrandDTOConvertor {

    public BrandDTO convertToBrandDTO(Brand brand){
        List<Long> productList = new ArrayList<>();
        BrandDTO brandDTO = new BrandDTO();

        brandDTO.setName(brandDTO.getName());
        brandDTO.setId(brand.getId());

        for (Product product : brand.getProducts()){
            productList.add(product.getId());
        }

        brandDTO.setProductsId(productList);

        return brandDTO;
    }

    public Brand convertToBrand(BrandDTO brandDTO,List<Product> products){
        List<Product> productList = new ArrayList<>();
        Brand brand = new Brand();

        brand.setName(brandDTO.getName());
        brand.setId(brandDTO.getId());

        for (Long productId : brandDTO.getProductsId()){
            productList.add(products.stream()
                    .filter(product -> product.getId().equals(productId))
                    .findFirst()
                    .orElse(null));
        }

       brand.setProducts(products);
        return brand;
    }

}
