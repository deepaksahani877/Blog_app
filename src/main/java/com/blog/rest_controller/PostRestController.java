package com.blog.rest_controller;



import com.blog.dto.CommentDto;
import com.blog.dto.PostDto;
import com.blog.services.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;


@RestController
@RequestMapping("/api/blog/")
public class PostRestController {

    private final PostService postService;

    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    List<PostDto> posts() {
        List<PostDto> posts = postService.getAllPosts();
        return posts;
    }
}

