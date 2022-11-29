package com.blog.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    @NotNull
    @NotEmpty(message = "Required")
    private String firstName;

    @NotNull
    @NotEmpty(message = "Required")
    private String lastName;

    @NotNull
    @NotEmpty(message = "Required")
    @Email(message = "Invalid Email")
    private String email;

    @NotNull
    @NotEmpty(message = "Required")
    private String password;
}
