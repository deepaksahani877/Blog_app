package com.blog.controller;


import com.blog.dto.CommentDto;
import com.blog.dto.PostDto;
import com.blog.mapper.PostMapper;
import com.blog.models.AlertMessage;
import com.blog.services.CommentService;
import com.blog.services.PostService;
import com.blog.utils.UrlUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class AdminBlogController {

    private final PostService postService;
    @Autowired
    private final CommentService commentService;

    public AdminBlogController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("/admin/posts")
    String Posts(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "admin/posts";
    }

    @GetMapping("/admin/posts/search")
    String searchPost(@RequestParam("query") String query,Model model) {
        model.addAttribute("posts", postService.searchPosts(query));
        return "admin/posts";
    }

    @GetMapping("/admin/post/create-post")
    String createPost(Model model) {
        if(!model.containsAttribute("post"))
            model.addAttribute("post", new PostDto());
        return "admin/create_post";
    }

    @PostMapping("/admin/post/save-post")
    RedirectView savePost(@ModelAttribute("post") @Valid PostDto post, BindingResult bindingResult, RedirectAttributes attr) {
        if (bindingResult.hasErrors()) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.post", bindingResult);
            attr.addFlashAttribute("post",post);
            return new RedirectView("/admin/post/create-post");
        }
        post.setUrl(UrlUtils.getUrl(post.getTitle()));
        postService.savePost(post);
        attr.addFlashAttribute("msg", new AlertMessage("Created", "Post created successfully.", "success"));
        return new RedirectView("/admin/posts");
    }

    @GetMapping("/admin/post/")
    String viewPost(@RequestParam("postUrl") String url, ModelMap modelMap) {
        modelMap.addAttribute("comment",new CommentDto());
        PostDto post = postService.getPostByUrl(url);
        modelMap.addAttribute("comments",commentService.getAllComments(post.getId()));
        modelMap.addAttribute("post", post);
        return "/admin/view_post";
    }

    @GetMapping("/admin/post/edit")
    String editPost(@RequestParam("postId") Long postId, ModelMap modelMap) {
        PostDto postDto = PostMapper.PostToPostDto(postService.getPostById(postId));
        modelMap.addAttribute("post", postDto);
        return "/admin/edit_post";
    }

    @PostMapping("/admin/post/edit")
    String commitEditPost(@ModelAttribute("post")@Valid PostDto postDto, BindingResult result,RedirectAttributes redirectAttributes) {
        postDto.setUrl(UrlUtils.getUrl(postDto.getTitle()));
        if(result.hasErrors()){
            return "/admin/edit_post";
        }
        int res = postService.updatePost(PostMapper.PostDtoToPost(postDto));
        if (res == PostService.STATUS_SUCCESS) {
            redirectAttributes.addFlashAttribute("msg",new AlertMessage("Updated","Post Successfully updated","primary"));
        } else {
            redirectAttributes.addFlashAttribute("msg",new AlertMessage("Fail","Post not updated","danger"));
        }
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/post/delete")
    String deletePost(@RequestParam("postId") Long id,RedirectAttributes redirectAttributes) {
        postService.deletePostById(id);
        redirectAttributes.addFlashAttribute("msg",new AlertMessage("Deleted","Post deleted successfully","danger"));
        return "redirect:/admin/posts";
    }


}
