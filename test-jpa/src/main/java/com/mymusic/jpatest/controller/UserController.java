package com.mymusic.jpatest.controller;

import com.mymusic.jpatest.dao.UserAndRoleDao;
import com.mymusic.jpatest.dto.UserAndRoleDto;
import com.mymusic.jpatest.entity.RoleEntity;
import com.mymusic.jpatest.entity.UserEntity;
import com.mymusic.jpatest.entity.UserV2Entity;
import com.mymusic.jpatest.management.UserManagement;
import com.mymusic.jpatest.repository.UserRepository;
import com.mymusic.jpatest.repository.UserV2Repository;
import com.mymusic.jpatest.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserV2Repository userV2Repository;

    @Autowired
    UserManagement userManagement;

    @GetMapping("/findById/{id}")
    public Optional<UserEntity> findById(@PathVariable Long id){
        Optional<UserEntity> user = userRepository.findById(id);
        return user;
    }

    @GetMapping("/get/{id}")
    public UserEntity get(@PathVariable Long id){
        UserEntity user = userRepository.getById(id);
        return user;
    }

    @GetMapping("/getInfo/{id}")
    public UserV2Entity getInfo(@PathVariable Long id){
//        UserEntity user = userRepository.getById(id);

        UserAndRoleDto dto = userRepository.getUserInfo(id);

        UserEntity user = dto.getUserEntity();

        UserV2Entity u = new UserV2Entity();

        u.setId(user.getId());
        u.setEmail(user.getEmail());
        u.setUpdated_at(user.getCreated_at());
        u.setUpdated_at(user.getUpdated_at());
        u.setName(user.getName());

        u.setRole(dto.getRoleEntity());
        return u;
    }

    @GetMapping("/getInfoV2/{id}")
    public UserV2Entity getInfoV2(@PathVariable Long id){

        UserAndRoleDao a = userV2Repository.getUserInfoV2(id);

        UserV2Entity u = new UserV2Entity();
        u.setId(a.getUser_id());
        u.setName(a.getName());
        u.setEmail(a.getEmail());
        u.setCreated_at(a.getCreated_at());
        u.setUpdated_at(a.getUpdated_at());

        RoleEntity r = new RoleEntity();
        r.setRole_name(a.getRole_name());
        r.setId(a.getRole_id());
        u.setRole(r);

        return u;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        UserEntity user = userRepository.getById(id);
        if (user != null) {
            userRepository.deleteByUserId(id);
            return "success";
        }
        return "not found";
    }

    @PostMapping("/save")
    public UserEntity save(@RequestParam Map<String, String> param) throws Exception {
        UserEntity user = new UserEntity();
        if (param.get("id") != null) {
            Long id = Long.valueOf(param.get("id"));
            if (id > 0) {
                user = userRepository.getById(id);

                if (user == null) {
                    return user;
                }
            }
        } else {
            user.setCreated_at(DateUtil.getTimestamp());
        }
        user.setName(param.get("name"));
        user.setEmail(param.get("email"));
        user.setUpdated_at(DateUtil.getTimestamp());
        userManagement.saveUser(user);
        return user;
    }
}
