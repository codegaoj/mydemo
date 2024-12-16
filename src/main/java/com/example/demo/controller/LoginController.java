package com.example.demo.controller;

import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/login/")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping ("graphcode")
    String graphcode(@RequestParam("param")String param) {
        return loginService.graphcode(param);
    }

    @PostMapping ("login")
    Map login(@RequestParam("username")String username,@RequestParam("code")String code){
        return loginService.login(username,code);
    }
}
