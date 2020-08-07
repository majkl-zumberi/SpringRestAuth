package com.auth.majkl.springsecurityjwt.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/resources/all")
public class TestResourceController {
    @GetMapping("")
    public String getAllResources(HttpServletRequest request){
        int userId= (Integer) request.getAttribute("id");
        return "Authenticated! "+ userId;
    }
}
