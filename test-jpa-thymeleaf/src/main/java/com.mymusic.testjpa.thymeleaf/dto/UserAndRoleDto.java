package com.mymusic.testjpa.thymeleaf.dto;

import com.mymusic.testjpa.thymeleaf.entity.RoleEntity;
import com.mymusic.testjpa.thymeleaf.entity.UserEntity;
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
