package com.mymusic.testjpa.thymeleaf.controller;

import com.mymusic.testjpa.thymeleaf.entity.UserEntity;
import com.mymusic.testjpa.thymeleaf.management.UserManagement;
import com.mymusic.testjpa.thymeleaf.repository.UserRepository;
import com.mymusic.testjpa.thymeleaf.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String index() {
        return "redirect:list";
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "/user/list";
    }

    @GetMapping("list2")
    public ModelAndView list2() {
        // This returns a JSON or XML with the users
        List<UserEntity> users = userRepository.findAll();
        for (UserEntity u : users){
            log.info(u.toString());
        }
        return new ModelAndView("user/list", "users", users);
    }
}
