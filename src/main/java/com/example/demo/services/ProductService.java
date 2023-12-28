package com.example.demo.services;

import com.example.demo.entity.Brand;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.entity.humans.User;
import com.example.demo.persistence.BrandRepository;
import com.example.demo.persistence.CategoryRepository;
import com.example.demo.persistence.UserRepository;
import com.example.demo.persistence.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;

    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, BrandRepository brandRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> findALl(){
        return productRepository.findAll();
    }

    public Product save(Product product){
       return productRepository.save(product);
    }

    public Product findById(Long id){
       Optional<Product> product =  productRepository.findById(id);
        return product.orElse(null);
    }

    public void deleteById(Long id){
        Optional<Product> productOptional = productRepository.findById(id);

        if(productOptional.isEmpty()){
            return;
        }

        Product product = productOptional.get();

        Brand brand = product.getBrand();
        brand.getProducts().remove(product);
        brandRepository.save(brand);

        for(User user : product.getWishlistsCustomers()){
            user.getFavoritesProducts().remove(product);
            userRepository.save(user);
        }

        for(Category category : product.getCategories()){
            category.getProducts().remove(product);
            categoryRepository.save(category);
        }

        productRepository.deleteById(product.getId());
    }

    public Product update (Product product){
        Optional<Product> productToUpdateOpt = productRepository.findById(product.getId());

        if(productToUpdateOpt.isEmpty()){
            return  null;
        }
        Product productToUpdate = productToUpdateOpt.get();

        productToUpdate.setBrand(product.getBrand());
        productToUpdate.setCategories(product.getCategories());
        productToUpdate.setName(product.getName());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setWishlistsCustomers(product.getWishlistsCustomers());
        productToUpdate.setQuantity(product.getQuantity());

        return productRepository.save(productToUpdate);
    }

}
