package org.example.BloggingProject.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class DefaultController {
    @RequestMapping("/")
    public String webMethod() {
        return "index";
    }

    @RequestMapping(method = {RequestMethod.OPTIONS,
    RequestMethod.GET}, value = "/**/{path:[^\\\\.]*}")
    public String redirectToIndex(@PathVariable String path){
        return "forward:/";
    }
}
