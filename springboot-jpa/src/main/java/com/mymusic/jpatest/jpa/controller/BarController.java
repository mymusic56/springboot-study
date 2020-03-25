package com.mymusic.jpatest.jpa.controller;

import lombok.extern.slf4j.Slf4j;
import com.mymusic.jpatest.jpa.dao.BarDao;
import com.mymusic.jpatest.jpa.dto.BarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bar")
@Slf4j
public class BarController {

    @Autowired
    BarDao barDao;

    @RequestMapping("findById")
    public BarDto findAll(int id){
        BarDto d = barDao.findById(id);
        return d;
    }
}
