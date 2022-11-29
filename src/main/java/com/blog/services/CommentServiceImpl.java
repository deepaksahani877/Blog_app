package com.blog.services;

import com.blog.dto.CommentDto;
import com.blog.entities.Comment;
import com.blog.entities.Post;
import com.blog.mapper.CommentMapper;
import com.blog.repositories.CommentRepository;
import com.blog.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void createComment(String postUrl, CommentDto commentDto) {
        Post post = postRepository.getPostByUrl(postUrl);
        Comment comment = CommentMapper.mapToComment(commentDto);
        comment.setPost(post);
        commentRepository.save(comment);

    }

    @Override
    public List<CommentDto> getAllCommentsById(long postId) {
        List<Comment> comments = commentRepository.getCommentByPostId(postId);
        return comments.stream().map(CommentMapper::mapToCommentDto).collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getAllComments() {
        return commentRepository.findAll().stream().map((CommentMapper::mapToCommentDto)).collect(Collectors.toList());
    }

    @Override
    public void deleteComment(long id) {
        commentRepository.deleteById(id);
    }
}
