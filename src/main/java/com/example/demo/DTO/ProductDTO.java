package com.example.demo.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO extends EntityDTO{

    private String name;

    private String description;

    private Double price;

    private Integer quantity;

    private Integer height;

    private Integer length;

    private Integer width;

    private MultipartFile image;

    private String brandName;

    private List<String> materialsName;

    private List<String> categoriesName;

    public ProductDTO(Long id, String name, String description, Double price,
                      Integer quantity, Integer height, Integer length, Integer width,
                      byte[] image, String brandName, List<String> materialsName, List<String> categoriesName) {
        super(id);
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.height = height;
        this.length = length;
        this.width = width;
//        this.image = image;
        this.brandName = brandName;
        this.materialsName = materialsName;
        this.categoriesName = categoriesName;
    }

    public ProductDTO(String name, String description, Double price, Integer quantity,
                      Integer height, Integer length, Integer width, Blob image, String brandName,
                      List<String> materialsName, List<String> categoriesName) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.height = height;
        this.length = length;
        this.width = width;
//        this.image = image;
        this.brandName = brandName;
        this.materialsName = materialsName;
        this.categoriesName = categoriesName;
    }
}
