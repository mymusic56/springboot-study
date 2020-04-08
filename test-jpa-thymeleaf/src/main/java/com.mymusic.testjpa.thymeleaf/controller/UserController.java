package com.mymusic.testjpa.thymeleaf.controller;

import com.mymusic.testjpa.thymeleaf.annotation.CommonCheck1;
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
    @CommonCheck1
    public ModelAndView list2() {
        // This returns a JSON or XML with the users
        List<UserEntity> users = userRepository.findAll();
        for (UserEntity u : users){
            log.info(u.toString());
        }
        return new ModelAndView("user/list", "users", users);
    }

    @GetMapping("/delete")
    public String delete(Model model, @RequestParam long id){
        userRepository.deleteByUserId(id);
        model.addAttribute("users", userRepository.findAll());
        return "/user/list";
    }

    @PostMapping("/add")
    public String add(Model model, @RequestParam Map<String, String> params){
        UserEntity u = new UserEntity();
        u.setName(params.get("name"));
        u.setEmail(params.get("email"));
        u.setCreated_at(DateUtil.getTimestamp());
        userRepository.save(u);

        model.addAttribute("users", userRepository.findAll());
        return "/user/list";
    }

    @GetMapping("edit")
    public ModelAndView edit(@RequestParam long id) {
        UserEntity u = userRepository.getById(id);
        return new ModelAndView("user/edit", "user", u);
    }


    @PostMapping("save")
    public ModelAndView save(@RequestParam Map<String,String> param) {
        UserEntity u = userRepository.getById(Long.valueOf(param.get("id")));
        if (u != null) {
            u.setName(param.get("name"));
            u.setEmail(param.get("email"));
            userRepository.save(u);
        }

        List<UserEntity> users = userRepository.findAll();
        return new ModelAndView("user/list", "users", users);
    }
}
