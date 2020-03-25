package com.mymusic.jpatest.jpa.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/helloWorld")
public class HelloWorldController {

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("getValue")
    public String getValue(@RequestParam(value = "name", defaultValue = "a") String name){
        //http://localhost:8080/helloWorld/getValue?name=zhangsan
        Jedis jedis = jedisPool.getResource();
        String a = jedis.get(name);
        log.info("----name="+a);
        return a;
    }

    @RequestMapping(value = "updateValue", method = RequestMethod.POST)
    public String updateValue(@RequestParam Map<String, String> params){
        String name = params.get("name");
        int age = Integer.valueOf(params.get("age"));
        log.info("----Params: name="+name+",age="+age);

        Jedis jedis = jedisPool.getResource();
        String a = jedis.set("name", name);

        return a;
    }

}
