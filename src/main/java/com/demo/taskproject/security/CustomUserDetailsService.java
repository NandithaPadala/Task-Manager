package com.demo.taskproject.security;

import com.demo.taskproject.entity.Users;
import com.demo.taskproject.exception.UserNotFoundException;
import com.demo.taskproject.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(email).orElseThrow(
                () -> new UserNotFoundException(String.format("User with email: %s is not found", email))
        );

        Set<String> roles = new HashSet<>();
        roles.add("USER");
        roles.add("ADMIN");

        return new User(user.getEmail(), user.getPassword(), getAuthorities(roles));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Set<String> roles) {
        return roles.stream().map(
                        SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}
