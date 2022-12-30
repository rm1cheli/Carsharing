package com.example.carsharing.controllers;

import com.example.carsharing.security.ClientDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "/hello";
    }

    @GetMapping("showUserInfo")
    public String showUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ClientDetails clientDetails = (ClientDetails)authentication.getPrincipal();
        System.out.println(clientDetails.getClient());
        return "/hello";
    }
}
