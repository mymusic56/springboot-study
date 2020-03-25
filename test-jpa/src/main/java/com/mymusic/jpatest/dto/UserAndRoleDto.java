package com.mymusic.jpatest.dto;

import com.mymusic.jpatest.entity.RoleEntity;
import com.mymusic.jpatest.entity.UserEntity;
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
