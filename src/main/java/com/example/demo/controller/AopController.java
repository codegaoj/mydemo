package com.example.demo.controller;

import com.example.demo.service.AopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
@RestController
@RequestMapping("/api/aop")
public class AopController{
    @Autowired
    private AopService aopService;

    @GetMapping("processRequest")
    void processRequest(@RequestParam("param")String param){
        aopService.processRequest(param);
    }
}
