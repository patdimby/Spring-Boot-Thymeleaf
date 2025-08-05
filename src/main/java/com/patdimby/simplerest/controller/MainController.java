package com.patdimby.simplerest.controller;

import com.patdimby.simplerest.dto.PostDto;
import com.patdimby.simplerest.dto.UserDto;
import com.patdimby.simplerest.model.UserRole;
import com.patdimby.simplerest.service.PostService;
import com.patdimby.simplerest.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/blog")
public class MainController {

    private final PostService postService;
    private final UserService userService;

    public MainController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/index")
    public String getIndex(Model model) {
        List<PostDto> posts = postService.findAllPosts();
        model.addAttribute("title", "Blog");
        model.addAttribute("posts", posts);
        return "blog/index";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("title", "Blog - Login");
        return "blog/login";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new UserDto());
        return "blog/registration";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserDto userDto) {
        userDto.setRole(UserRole.ROLE_ADMIN);
        System.out.println(userDto);
        userService.saveUserDto(userDto);
        return "redirect:blog/login";
    }
}
