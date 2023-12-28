package com.example.demo.services;

import com.example.demo.entity.humans.User;
import java.util.List;

public interface IUserService {

    User findById(Long id);
    List<User> findAll();
    User save(User user);
    void deleteById(Long id);
    User update(User user,Long id);
}
