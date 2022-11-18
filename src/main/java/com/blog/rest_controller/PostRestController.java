package com.blog.rest_controller;

import com.blog.dto.PostDto;
import com.blog.mapper.PostMapper;
import com.blog.services.PostService;
import com.blog.utils.UrlUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class PostRestController {

    final PostService postService;

    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/api/blog/posts")
    List<PostDto> getAllPosts(){
        return postService.getAllPosts();
    }
    @PutMapping("/api/blog/post/update")
    void updatePost(@RequestBody PostDto post){
        postService.updatePost(PostMapper.PostDtoToPost(post));
    }

    @PostMapping("/api/blog/post/create")
    void createPost(@RequestBody PostDto post){
        postService.updatePost(PostMapper.PostDtoToPost(post));
    }

    @DeleteMapping("/api/blog/post/delete/{id}")
    void deletePost(@PathVariable long id){
        postService.deletePostById(id);
    }

}
