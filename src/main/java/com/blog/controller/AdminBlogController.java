package com.blog.controller;


import com.blog.dto.PostDto;
import com.blog.mapper.PostMapper;
import com.blog.services.PostService;
import com.blog.utils.UrlUtils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class AdminBlogController {

    final PostService postService;

    public AdminBlogController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/admin/posts")
    String Posts(Model model){
        model.addAttribute("posts",postService.getAllPosts());
        return "admin/posts";
    }
    @GetMapping("/admin/posts/search")
    String searchPost(Model model,@RequestParam("query") String query){

        model.addAttribute("posts",postService.searchPosts(query));
        return "admin/posts";
    }

    @GetMapping("/admin/post/create-post")
    String createPost(Model model){
        model.addAttribute("post",new PostDto());
        return "admin/create_post";
    }

    @PostMapping("/admin/post/save-post")
    String savePost(@ModelAttribute("post") @Valid  PostDto post, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "admin/create_post";
        }
        post.setUrl(UrlUtils.getUrl(post.getTitle()));
        postService.savePost(post);
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/post/")
    String viewPost(@RequestParam("postUrl") String url,ModelMap modelMap){
        modelMap.addAttribute("post",postService.getPostByUrl(url));
        return "/admin/view_post";
    }

    @GetMapping("/admin/post/edit")
    String editPost(@RequestParam("postId") Long postId, ModelMap modelMap){
        PostDto postDto = PostMapper.PostToPostDto(postService.getPostById(postId));
        modelMap.addAttribute("post",postDto);
        return "/admin/edit_post";
    }

    @PostMapping("/admin/post/edit")
    String commitEditPost(@ModelAttribute("post") PostDto postDto, RedirectAttributes redirectAttributes) {
        postDto.setUrl(UrlUtils.getUrl(postDto.getTitle()));
        int res = postService.updatePost(PostMapper.PostDtoToPost(postDto));
        if(res==PostService.STATUS_SUCCESS){
            System.out.println("Successful");
        }else{
            System.out.println("    Fail");
        }
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/post/delete")
    String deletePost(@RequestParam("postId") Long id) {
        postService.deletePostById(id);
        return "redirect:/admin/posts";
    }


}
