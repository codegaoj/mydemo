package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login/")
public class LoginController {

    @PostMapping ("graphcode")
    void login(@RequestParam("param")String param){
        aopService.processRequest(param);

    @PostMapping ("login")
    void login(@RequestParam("param")String param){
        aopService.processRequest(param);
    }
}
