package com.example.demo.service;


import java.util.Map;

public interface LoginService {

    public String graphcode(String param);


    public Map login(String username,String code);
}
