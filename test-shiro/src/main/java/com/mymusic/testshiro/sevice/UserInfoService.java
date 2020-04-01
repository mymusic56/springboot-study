package com.mymusic.testshiro.sevice;

import com.mymusic.testshiro.entity.UserInfo;

public interface UserInfoService {
    /**通过username查找用户信息;*/
    public UserInfo findByUsername(String username);
}