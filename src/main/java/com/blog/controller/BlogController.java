package com.blog.controller;

import com.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {

    final PostService postService;
    public BlogController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(value = "/")
    String Home(Model model){
        model.addAttribute("postsResponse",postService.getAllPosts());
        return "blog/home";
    }

    @GetMapping(value = "/search")
    String searchPosts(Model model, @RequestParam("query") String query){
        model.addAttribute("postsResponse",postService.searchPosts(query));
        return "blog/home";
    }

    @GetMapping("/post/{url}")
    String viewPost(@PathVariable("url") String url, ModelMap model){
        model.addAttribute("post", postService.getPostByUrl(url));
        return "blog/view_post";
    }

}
