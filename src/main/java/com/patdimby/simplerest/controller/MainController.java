package com.patdimby.simplerest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.ui.Model;

@Controller
public class MainController {  

    @GetMapping("blog/index")
    public String getIndex() {

        return "blog/index";
    }
}
