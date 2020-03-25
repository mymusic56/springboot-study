package com.mymusic.testmybatis.enums;

import lombok.Getter;

@Getter
public enum UserSexEnum {
    MAN(1,"男"), WOMAN(2,"女");

    private int code;
    private String desc;

    private UserSexEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }
}
