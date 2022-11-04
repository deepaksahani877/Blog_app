package com.blog.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PostDto{
    long id;
    @NotEmpty(message = "Title cannot be empty.")
    String title;
    String url;
    @NotEmpty(message = "Content cannot be empty")
    String content;
    @NotEmpty(message = "short Description cannot be empty")
    String shortDescription;
    LocalDateTime createdOn;
    LocalDateTime updatedOn;

}
