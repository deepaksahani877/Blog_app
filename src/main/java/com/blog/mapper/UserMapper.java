package com.blog.mapper;

import com.blog.dto.UserDto;
import com.blog.entities.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public static User mapToUser(UserDto userDto){
        return User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }
}
