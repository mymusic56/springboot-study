package com.mymusic.jpatest.jpa.controller;

import com.mymusic.jpatest.jpa.dto.TestUserDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/testUser")
public class TestUserController {


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public TestUserDto save(@RequestParam Map<String, String> params){
        TestUserDto user = new TestUserDto();
        user.setId(Integer.valueOf(params.get("id")));
        user.setName(params.get("name"));
        return user;
    }
}
