package com.blog.services;

import com.blog.dto.UserDto;
import com.blog.entities.User;
import com.blog.mapper.UserMapper;
import com.blog.repositories.RoleRepository;
import com.blog.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;


@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(Collections.singletonList(roleRepository.getRoleByName("ROLE_GUEST")));
        userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }
}
