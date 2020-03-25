package com.mymusic.jpatest.jpa.dto;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

@Data
public class TestUserDto {
    int id;
    String name;
}
