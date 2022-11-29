package com.blog.security;


import com.blog.entities.User;
import com.blog.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(email);
        if(user!=null){
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    user.getRoles().stream().map((role)->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())

            );
        }else{
            throw new UsernameNotFoundException("Invalid username and password");
        }
    }
}
