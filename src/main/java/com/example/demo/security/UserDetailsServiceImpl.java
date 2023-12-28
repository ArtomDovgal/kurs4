package com.example.demo.security;

import com.example.demo.entity.humans.User;
import com.example.demo.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    UserRepository userRepository;
    @Override
    public UserDetailsImpl loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email).
                orElseThrow(()->new UsernameNotFoundException("Користвача з імейлом" + email + "не знайдено"));
        return UserDetailsImpl.build(user);
    }
}
