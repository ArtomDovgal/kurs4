package com.example.demo.security;

import com.example.demo.entity.Role;
import com.example.demo.entity.humans.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String email;
    private String password;
    private Role role;
    private String firstName;
    private String lastName;
    private Long basketId;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String email, String password, Role role, String firstName,
                                  String lastName, Collection<? extends GrantedAuthority> authorities,Long cardId) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.authorities = authorities;
        this.basketId = cardId;
    }

    public static UserDetailsImpl build(User user){
        List<GrantedAuthority> authorityList = List.of(new SimpleGrantedAuthority(user.getRole().getTypeRole().toString()));

        return  new UserDetailsImpl(user.getId(),user.getEmail(),user.getPassword(),user.getRole(),user.getFirstName(),
                user.getLastName(),authorityList,user.getBasket().getId());

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
