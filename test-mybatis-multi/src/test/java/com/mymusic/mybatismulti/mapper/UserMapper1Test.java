package com.mymusic.mybatismulti.mapper;

import com.mymusic.mybatismulti.entity.UserEntity;
import com.mymusic.mybatismulti.enums.UserSexEnum;
import com.mymusic.mybatismulti.mapper.test1.User1Mapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapper1Test {

    @Autowired
    User1Mapper user1Mapper;
    @Test
    public void insertTest(){
        user1Mapper.insert(new UserEntity("zhangsan","张三","123456", UserSexEnum.MAN.getCode()));
        user1Mapper.insert(new UserEntity("xiaoming","小明","123456", UserSexEnum.MAN.getCode()));
        user1Mapper.insert(new UserEntity("xiaohong","小红","123456", UserSexEnum.WOMAN.getCode()));

        Assert.assertEquals(3, user1Mapper.getAll().size());
    }
}
