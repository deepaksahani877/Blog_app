package com.blog.mapper;

import com.blog.dto.PostDto;
import com.blog.entities.Post;

import java.util.stream.Collectors;

public class PostMapper {

    public static PostDto PostToPostDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .url(post.getUrl())
                .content(post.getContent())
                .shortDescription(post.getShortDescription())
                .createdOn(post.getCreatedOn())
                .updatedOn(post.getUpdatedOn())
                .comments(post.getComments().stream().map(CommentMapper::mapToCommentDto).collect(Collectors.toSet()))
                .build();
    }

    public static Post PostDtoToPost(PostDto postDto) {
        return Post.builder()
                .id(postDto.getId())
                .title(postDto.getTitle())
                .url(postDto.getUrl())
                .content(postDto.getContent())
                .shortDescription(postDto.getShortDescription())
                .createdOn(postDto.getCreatedOn())
                .updatedOn(postDto.getUpdatedOn()).build();
    }

}
