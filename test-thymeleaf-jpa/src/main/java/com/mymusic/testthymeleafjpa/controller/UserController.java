package com.mymusic.testthymeleafjpa.controller;

import com.mymusic.jpatest.common.util.DateUtil;
import com.mymusic.testthymeleafjpa.annotation.CommonCheck1;
import com.mymusic.testthymeleafjpa.entity.UserEntity;
import com.mymusic.testthymeleafjpa.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

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
            u.setUpdated_at(DateUtil.getTimestamp());
            userRepository.save(u);
        }

        List<UserEntity> users = userRepository.findAll();
        return new ModelAndView("user/list", "users", users);
    }
}
