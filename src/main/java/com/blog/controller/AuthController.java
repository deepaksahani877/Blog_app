package com.blog.controller;


import com.blog.dto.UserDto;
import com.blog.entities.User;
import com.blog.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String userRegistration(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user",user);
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") UserDto user, BindingResult bindingResult,Model model){
        User existingUser = userService.getUserByEmail(user.getEmail());

        if(existingUser!=null && existingUser.getEmail()!=null && !existingUser.getEmail().isEmpty()){
            bindingResult.rejectValue("email","USER_ALREADY_REGISTERED","Email already registered with us");
        }
        if(bindingResult.hasErrors()){
            model.addAttribute("user",user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

}
