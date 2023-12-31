package com.blog.services;

import com.blog.dto.CommentDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CommentService {
    void createComment(String postUrl, CommentDto commentDto);
    List<CommentDto> getAllCommentsById(long postId);
    List<CommentDto> getAllComments();
    void deleteComment(long id);
}
