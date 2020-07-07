package jrbour.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class UserController {
    @GetMapping("/")
    public String hello(){
        return "Hello world !";
    }
}
