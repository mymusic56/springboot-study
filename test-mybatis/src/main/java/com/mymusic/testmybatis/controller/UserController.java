package com.mymusic.testmybatis.controller;

import com.mymusic.jpatest.common.util.DateUtil;
import com.mymusic.testmybatis.entity.UserEntity;
import com.mymusic.testmybatis.enums.UserSexEnum;
import com.mymusic.testmybatis.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/add")
    public UserEntity add(@RequestParam Map<String,String> params){
        UserEntity user = new UserEntity();

        user.setName(params.get("name"));
        user.setNickName(params.get("nick_name"));
        user.setEmail(params.get("email"));

        if (UserSexEnum.MAN.getDesc().equals(params.get("sex"))) {
            user.setUserSex(UserSexEnum.MAN.getCode());
        } else {
            user.setUserSex(UserSexEnum.WOMAN.getCode());
        }

        user.setCreated_at(DateUtil.getTimestamp());

        Long id = userMapper.insert(user);
        return user;
    }

    @GetMapping("/get/{id}")
    public UserEntity get(@PathVariable Long id){
        UserEntity u = userMapper.getOne(id);
        return u;
    }

    @GetMapping("/all")
    public List<UserEntity> all(){
        return userMapper.getAll();
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Map<String, String> params){
        UserEntity u = userMapper.getOne(Long.valueOf(params.get("id")));
        if (u == null) {
            return "用户不存在";
        }
        int a = userMapper.delete(Long.valueOf(params.get("id")));
        if (a > 0) {
            return "删除成功";
        }
        return "删除失败";
    }

    @PostMapping("/update")
    public String update(@RequestParam Map<String, String> params){
        UserEntity u = userMapper.getOne(Long.valueOf(params.get("id")));
        if (u == null) {
            return "用户不存在";
        }
        u.setName(params.get("name"));
        u.setNickName(params.get("nick_name"));
        u.setUpdated_at(DateUtil.getTimestamp());

        int a = userMapper.update(u);

        if (a > 0) {
            return "修改成功";
        }
        return "修改失败";
    }
}
