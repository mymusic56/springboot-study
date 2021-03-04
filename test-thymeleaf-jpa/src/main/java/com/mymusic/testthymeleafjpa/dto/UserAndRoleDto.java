package com.mymusic.testthymeleafjpa.dto;

import com.mymusic.testthymeleafjpa.entity.RoleEntity;
import com.mymusic.testthymeleafjpa.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserAndRoleDto {

    private UserEntity userEntity;

    private RoleEntity roleEntity;

    public UserAndRoleDto(UserEntity u, RoleEntity r){
        this.userEntity = u;
        this.roleEntity = r;
    }
}
