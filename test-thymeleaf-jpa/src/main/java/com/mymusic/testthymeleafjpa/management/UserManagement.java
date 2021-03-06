package com.mymusic.testthymeleafjpa.management;

import com.mymusic.jpatest.common.util.DateUtil;
import com.mymusic.testthymeleafjpa.entity.LogEntity;
import com.mymusic.testthymeleafjpa.entity.UserEntity;
import com.mymusic.testthymeleafjpa.repository.LogRepository;
import com.mymusic.testthymeleafjpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserManagement {

    private final UserRepository userRepository;

    private final LogRepository logRepository;

    @Autowired
    public UserManagement(UserRepository userRepository, LogRepository logRepository){
        this.userRepository = userRepository;
        this.logRepository = logRepository;
    }

    @Transactional
    public void saveUser(UserEntity user) {
        userRepository.save(user);
        LogEntity log = new LogEntity();
        log.setAction("saveUser");
        log.setCreated_at(DateUtil.getTimestamp());
        log.setUser_id(0);
        log.setRequest_param(user.toString());
        logRepository.save(log);
    }
}
