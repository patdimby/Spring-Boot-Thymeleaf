package com.patdimby.simplerest.controller;

import com.patdimby.simplerest.dto.PostDto;
import com.patdimby.simplerest.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class MainController {  

	private PostService postService;

    public MainController(PostService postService) {
        this.postService = postService;
    }
	
	// create handler method, GET request and return model and view
    @GetMapping("blog/index")
    public String getIndex(Model model) {
		List<PostDto> posts = postService.findAllPosts();
        model.addAttribute("posts", posts);
        return "blog/index";
    }
}
