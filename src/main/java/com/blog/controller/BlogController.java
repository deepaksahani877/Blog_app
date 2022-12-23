package com.blog.controller;

import com.blog.dto.CommentDto;
import com.blog.dto.PostDto;
import com.blog.services.CommentService;
import com.blog.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;


@Controller
public class BlogController {

    final PostService postService;
    final CommentService commentService;
    public BlogController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping(value = "/")
    String Home(Model model){
        int pageNo=1;
        List<PostDto> posts = postService.getAllPosts();
        model.addAttribute("postsResponse",getPostByPageNo(posts,pageNo));
        model.addAttribute("pageNo",pageNo);
        model.addAttribute("totalPosts",posts.size());
        return "blog/home";
    }

    @GetMapping(value = "/search")
    String searchPosts(Model model, @RequestParam("query") String query,@RequestParam("page") int page){
        List<PostDto> posts = postService.searchPosts(query);
        model.addAttribute("postsResponse",getPostByPageNo(posts,page));
        model.addAttribute("pageNo",page);
        model.addAttribute("query",query);
        model.addAttribute("totalPosts",posts.size());
        return "blog/search_post";
    }

    @GetMapping("/post/{url}")
    String viewPost(@PathVariable("url") String url, ModelMap model){
        PostDto post =  postService.getPostByUrl(url);
        model.addAttribute("post",post);
        model.addAttribute("comments",commentService.getAllCommentsById(post.getId()));
        model.addAttribute("comment",new CommentDto());
        return "blog/view_post";
    }


    @GetMapping("/page/{pageNo}")
    String viewPostByPage(@PathVariable("pageNo") int page,Model model){
        List<PostDto> posts = postService.getAllPosts();
        model.addAttribute("postsResponse",getPostByPageNo(posts,page));
        model.addAttribute("pageNo",page);
        model.addAttribute("totalPosts",posts.size());
        return "blog/home";
    }

    private static List<PostDto> getPostByPageNo(List<PostDto> posts,int pageNo) {
        List<PostDto> currentPagePosts;
        int k = 6;//Number of post per page
        if (k <= posts.size() && pageNo > 0) {
            if (k * pageNo < posts.size()) {
                currentPagePosts = posts.subList(pageNo * k - k, pageNo * k);
            } else {
                currentPagePosts = pageNo * k - k <= posts.size() ? posts.subList(pageNo * k - k, posts.size()) : new ArrayList<>();
            }
        }else{
            currentPagePosts = posts;
        }
        return currentPagePosts;
    }
}
