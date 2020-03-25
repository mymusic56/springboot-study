package com.mymusic.mybatismulti.entity;

import com.mymusic.mybatismulti.util.DateUtil;
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

    public UserEntity(String name, String nickName, String password, int userSex){
        this.name = name;
        this.nickName = nickName;
        this.password = password;
        this.userSex = userSex;
        this.created_at = DateUtil.getTimestamp();
    }

    public Date getCreatedDate (int created_at){
        return new Date(created_at);
    }

    public String toString() {
        return "name: " + this.name + ", email: " + this.email;
    }
}
