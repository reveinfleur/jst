package com.io.jst.controllers.controller;

import com.io.jst.model.dto.UsersDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserViewController {


    @GetMapping("/login")
    public String login(){
        return "/user/loginForm";
    }
    @GetMapping("/join")
    public String join(){
        return "/user/joinForm";
    }


}
