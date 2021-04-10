package org.example.BloggingProject.controllers;



import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DefaultController {
    @RequestMapping("/")
    public String webMethod(Model model) {
        return "index";
    }
}
