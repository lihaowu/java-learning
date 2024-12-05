package com.leopold.javalearning.springboot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {

    @PostMapping("doLogin")
    public String login() {
        System.out.println("登录");
        return "index.html";
    }
}
