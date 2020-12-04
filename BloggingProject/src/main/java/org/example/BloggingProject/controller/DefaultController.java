package org.example.BloggingProject.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DefaultController {
    @GetMapping("/")
    public String webMethod(Model model) {
        return "index";
    }
}
