package com.blog.dto;

import com.blog.entities.Post;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
    private Long id;
    @NotEmpty(message = "Name cannot be blank")
    private String name;
    @Email
    @NotEmpty(message = "Email cannot be blank")
    private String email;
    @NotEmpty(message = "message cannot be blank")
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Post post;
}
