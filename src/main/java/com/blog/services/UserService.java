package com.blog.services;

import com.blog.dto.UserDto;
import com.blog.entities.User;

public interface UserService {
    void saveUser(UserDto user);
    User getUserByEmail(String email);
}
