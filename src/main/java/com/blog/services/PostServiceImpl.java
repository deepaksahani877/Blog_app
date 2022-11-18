package com.blog.services;

import com.blog.dto.PostDto;
import com.blog.entities.Post;
import com.blog.mapper.PostMapper;
import com.blog.repositories.PostRepository;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        Collections.reverse(posts);
        return posts.stream().map(PostMapper::PostToPostDto).collect(Collectors.toList());
    }

    @Override
    public void savePost(PostDto postDto) {
        postRepository.save(PostMapper.PostDtoToPost(postDto));
    }

    @Override
    public Post getPostById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        return post.orElse(null);
    }

    @Override
    public int updatePost(Post post) {
        int status = 0;
        try {
            postRepository.save(post);
        } catch (Exception e) {
            status = -1;
        }
        return status;
    }

    @Override
    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public PostDto getPostByUrl(String url) {
        return PostMapper.PostToPostDto(postRepository.getPostByUrl(url));
    }

    @Override
    public List<PostDto> searchPosts(String query) {
        List<Post> posts = postRepository.searchPosts(query);
        Collections.reverse(posts);
        return posts.stream().map((PostMapper::PostToPostDto)).collect(Collectors.toList());
    }


}
