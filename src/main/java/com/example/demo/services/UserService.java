package com.example.demo.services;

import com.example.demo.entity.Basket;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.entity.Role;
import com.example.demo.entity.humans.User;
import com.example.demo.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final BasketRepository basketRepository;
    private final RoleRepository roleRepository;

    private BCryptPasswordEncoder encoder(){
        return  new BCryptPasswordEncoder();
    }

    public UserService(UserRepository userRepository, OrderRepository orderRepository, ProductRepository productRepository, BasketRepository basketRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.basketRepository = basketRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User findById(Long id) {
        if(userRepository.findById(id).isPresent()){
            return userRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        user.setPassword(encoder().encode(user.getPassword()));
        if(user.getBasket() == null){
            Basket basket = new Basket();
            basket.setUser(user);
            user.setBasket(basket);
            User userSaved = userRepository.save(user);
            basketRepository.save(basket);
            return userSaved;
        }else{
            return userRepository.save(user);
        }
    }
    public User save(User user, Role role) {
        User savedUser;
        user.setPassword(encoder().encode(user.getPassword()));
        user.setRole(role);

        if(user.getBasket() == null){
            Basket basket = new Basket();
            basket.setUser(user);
            user.setBasket(basket);
            savedUser = userRepository.save(user);
            basketRepository.save(basket);
            role.getUsers().add(savedUser);
            roleRepository.save(role);
        }else{
            savedUser = userRepository.save(user);
            role.getUsers().add(savedUser);
            roleRepository.save(role);
        }
            return savedUser;
    }

    @Override
    public void deleteById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            List<Order> orders = user.get().getOrders();
            List<Product> products = user.get().getFavoritesProducts();
            for(Order order : orders){
                order.setUser(null);
                orderRepository.save(order);
            }
            for(Order order : orders){
                order.setUser(null);
                orderRepository.save(order);
            }
            for(Product product : products){
                product.getWishlistsCustomers().remove(user.get());
                productRepository.save(product);
            }
            userRepository.delete(user.get());
        }
    }

    @Override
    public User update(User user, Long id) {
        if(userRepository.findById(id).isEmpty()){
            return null;
        }

        User userToUpdate = userRepository.findById(id).get();
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setAddress(user.getAddress());
        userToUpdate.setBasket(user.getBasket());
        userToUpdate.setPhone(user.getPhone());
        userToUpdate.setFavoritesProducts(user.getFavoritesProducts());
        userToUpdate.setOrders(user.getOrders());

        return userRepository.save(userToUpdate);
    }
}
