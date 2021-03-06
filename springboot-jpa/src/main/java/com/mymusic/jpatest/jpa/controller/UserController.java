package com.mymusic.jpatest.jpa.controller;

import com.mymusic.jpatest.jpa.entity.User;
import com.mymusic.jpatest.jpa.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;

@Controller
@RequestMapping(path = "/user")
@Slf4j
public class UserController {

    @Autowired
    // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody
    RedirectView add (@RequestParam String name
            , @RequestParam String email, RedirectAttributes redirectAttributes) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User n = new User();
        n.setName(name);
        n.setEmail(email);
        n.setCreated_at(Long.valueOf(String.valueOf(new Date().getTime()/1000)));
        log.info("时间戳"+n.getCreated_at());
        userRepository.save(n);


        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("/user/list");

        redirectAttributes.addAttribute("userList", userRepository.findAll());
        return  redirectView;
    }

    @GetMapping("list")
    public ModelAndView list() {
        // This returns a JSON or XML with the users
        return new ModelAndView("user/list", "userList", userRepository.findAll());
    }
}
