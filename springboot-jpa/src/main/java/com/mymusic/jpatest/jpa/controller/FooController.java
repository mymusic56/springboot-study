package com.mymusic.jpatest.jpa.controller;

import com.mymusic.jpatest.jpa.dao.BarDao;
import lombok.extern.slf4j.Slf4j;
import com.mymusic.jpatest.jpa.dao.FooDao;
import com.mymusic.jpatest.jpa.dto.FooDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/foo")
@Slf4j
public class FooController {

    @Autowired
    FooDao fooDao;

    @Autowired
    BarDao barDao;

    @RequestMapping("findAll")
    public List<Map<String, Object>> findAll(){
        List<Map<String, Object>> d = fooDao.findAll();

        return d;
    }

    @RequestMapping("findById")
    public FooDto findById(int id){
//        http://localhost:8080/foo/findById?id=1
        FooDto d = fooDao.findById(id, 1);

        return d;
    }

    @GetMapping("/{id}")
    public FooDto findByIdV2(@PathVariable int id){
        //http://localhost:8080/foo/1
        FooDto d = fooDao.findById(id, 1);

        return d;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Map<String, String> save(@RequestParam Map<String, String> params) {
        Map<String, String> row = fooDao.insertData(params);
        return row;
    }
}
