package com.blog.controller;


import com.blog.dto.CommentDto;
import com.blog.dto.PostDto;
import com.blog.services.CommentService;
import com.blog.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;
    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    @RequestMapping("/comments/{postUrl}")
    public String createComment(@PathVariable("postUrl") String postUrl, @Valid @ModelAttribute("comment")CommentDto comment, BindingResult bindingResult, Model model) {
        PostDto postDto = postService.getPostByUrl(postUrl);
        if(bindingResult.hasErrors()){
            model.addAttribute("comments",commentService.getAllComments(postDto.getId()));
            model.addAttribute("post",postDto);
            model.addAttribute("comment",comment);
            return "blog/view_post";
        }

        commentService.createComment(postUrl,comment);
        return "redirect:/post/"+postUrl;
    }
}
