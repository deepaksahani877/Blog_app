package com.blog.services;

import com.blog.dto.PostDto;
import com.blog.entities.Post;
import com.blog.mapper.PostMapper;

import java.util.List;

public interface PostService {
    int STATUS_SUCCESS = 0;
    int STATUS_FAIL = -1;
    List<PostDto> getAllPosts();
    void savePost(PostDto postDto);
    Post getPostById(Long id);
    int updatePost(Post post);

    void deletePostById(Long id);

    PostDto getPostByUrl(String url);
    List<PostDto> searchPosts(String query);
}
