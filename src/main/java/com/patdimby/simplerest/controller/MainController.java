package com.patdimby.simplerest.controller;

import com.patdimby.simplerest.dto.UserDto;
import com.patdimby.simplerest.model.Blog;
import com.patdimby.simplerest.model.Event;
import com.patdimby.simplerest.model.Team;
import com.patdimby.simplerest.model.UserRole;
import com.patdimby.simplerest.service.BlogService;
import com.patdimby.simplerest.service.EventService;
import com.patdimby.simplerest.service.TeamService;
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
//@PreAuthorize("hasRole('ROLE_ADMIN')")
public class MainController {
   
	private final BlogService blogService;
	private final EventService eventService;
	private final TeamService teamService;
    private final UserService userService;

    public MainController(BlogService blogService, EventService eventService, TeamService teamService, UserService userService) {
        this.blogService = blogService;
		this.eventService = eventService;
		this.teamService = teamService;
        this.userService = userService;
    }

    @GetMapping("/index")
    public String getIndex(Model model) {
        List<Blog> blogs = blogService.getAllBlogs();
		model.addAttribute("blogs", blogs);
		List<Team> teams = teamService.getAllTeams();
		model.addAttribute("teams", teams);
		List<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
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
        model.addAttribute("title", "Blog - Register");
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
