package com.mymusic.jpatest.jpa.repository;

import com.mymusic.jpatest.jpa.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JapSaveTest {
    @Resource
    CustomerJpaRepository customerJpaRepository;

    @Test
    public void testSave(){
        customerJpaRepository.save(new Customer("Jack", "Bauer", "Jack@163.com"));
    }
}
