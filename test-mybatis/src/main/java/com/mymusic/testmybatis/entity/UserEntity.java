package com.mymusic.testmybatis.entity;

import com.mymusic.testmybatis.enums.UserSexEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserEntity {
    private Long id;

    private String name;

    private String nickName;
    private String password;

    private int userSex;

    private String email;

    private int role_id;

    private int created_at;
    private int updated_at;

    public Date getCreatedDate (int created_at){
        return new Date(created_at);
    }

    public String toString() {
        return "name: " + this.name + ", email: " + this.email;
    }
}
