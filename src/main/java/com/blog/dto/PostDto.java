package com.blog.dto;

import com.blog.entities.Comment;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PostDto{
    private long id;
    @NotEmpty(message = "Title cannot be empty.")
    private String title;
    private String url;
    @NotEmpty(message = "Content cannot be empty")
    private String content;
    @NotEmpty(message = "short Description cannot be empty")
    private String shortDescription;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Set<CommentDto> comments;


}
