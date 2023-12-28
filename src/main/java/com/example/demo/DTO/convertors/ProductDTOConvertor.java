package com.example.demo.DTO.convertors;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.entity.Brand;
import com.example.demo.entity.Category;
import com.example.demo.entity.Material;
import com.example.demo.entity.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductDTOConvertor {

    public static ProductDTO convertToProductDTO(Product product) {
        List<String> materialsId = new ArrayList<>();

        List<String> categoriesId = new ArrayList<>();

        String brandName = "";

        if (product.getBrand() != null) brandName = product.getBrand().getName();

        for (Category category : product.getCategories()) {
            categoriesId.add(category.getName());
        }
        for (Material material : product.getMaterials()) {
            materialsId.add(material.getName());
        }

        return new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice(),
                product.getQuantity(), product.getHeight(), product.getLength(), product.getWidth(), product.getImage()
                , brandName, materialsId, categoriesId);
    }

    public static Product convertToProduct(ProductDTO product,List<Brand> brands,List<Material> materials, List<Category> categories) throws IOException {
        List<Material> productMaterials = new ArrayList<>();

        List<Category> productCategories = new ArrayList<>();

        Brand brand =brands.stream()
                .filter(brand1 -> brand1.getName().equals(product.getBrandName()))
                .findFirst()
                .orElse(null);


        for (String categoryName : product.getCategoriesName()) {
            productCategories.add(categories.stream()
                    .filter(category -> category.getName().equals(categoryName))
                    .findFirst()
                    .orElse(null));
        }
        for (String materialName : product.getMaterialsName()) {
            productMaterials.add(materials.stream()
                    .filter(material -> material.getName().equals(materialName))
                    .findFirst()
                    .orElse(null));
        }

        return new Product(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity(),
                product.getHeight(), product.getLength(), product.getWidth(), product.getImage().getBytes(),
                productMaterials, productCategories, brand, new ArrayList<>());
    }
}

