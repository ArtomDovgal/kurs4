package com.example.demo.controllers;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.DTO.convertors.ProductDTOConvertor;
import com.example.demo.entity.Brand;
import com.example.demo.entity.Category;
import com.example.demo.entity.Material;
import com.example.demo.entity.Product;
import com.example.demo.services.BrandService;
import com.example.demo.services.CategoryService;
import com.example.demo.services.MaterialService;
import com.example.demo.services.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final BrandService  brandService;
    private final MaterialService materialService;

    public ProductController(ProductService productService, CategoryService categoryService, BrandService brandService, MaterialService materialService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.brandService = brandService;
        this.materialService = materialService;
    }

    @GetMapping("products")
    public String getProducts(Model model){
        model.addAttribute("products",productService.findALl());
        model.addAttribute("categories",categoryService.findAll());
        return "main/product/products";
    }
    @GetMapping("/product/image/{id}")
    public ResponseEntity<byte[]> showProductImage(@PathVariable String id) {
        Product product = productService.findById(Long.parseLong(id));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(product.getImage(), headers, HttpStatus.OK);
    }
    @GetMapping("product/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new ProductDTO());
        model.addAttribute("brands",brandService.findAll().stream().map(Brand::getName)
                .collect(Collectors.toList()));
        model.addAttribute("categories",categoryService.findAll().stream().map(Category::getName)
                .collect(Collectors.toList()));
        model.addAttribute("materials",materialService.findAll().stream().map(Material::getName)
                .collect(Collectors.toList()));
        return "admin/add_product";
    }
    @PostMapping("product/add")
    public String saveOrUpdateProduct(@ModelAttribute ProductDTO productDTO) throws IOException {
       Product product =  ProductDTOConvertor.convertToProduct(productDTO,brandService.findAll(),materialService.findAll(),categoryService.findAll());
        productService.save(product);
        return "redirect:admin/main_page";
    }

    @PostMapping("product/{id}/update")
    public String updateProduct(@PathVariable String id, Model model) {
        model.addAttribute("product", productService.findById(Long.parseLong(id)));
        return "admin/add_product";
    }
}
