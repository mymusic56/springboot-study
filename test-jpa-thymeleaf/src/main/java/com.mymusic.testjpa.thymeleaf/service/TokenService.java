package com.mymusic.testjpa.thymeleaf.service;

import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;

public class TokenService {
    public boolean check(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getParameter("token");
        return "1".equals(token);
    }
}
