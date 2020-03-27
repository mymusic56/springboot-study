package com.mymusic.testjpa.thymeleaf.dao;

public interface UserAndRoleDao {
    Long getUser_id();
    Long getRole_id();
    String getName();
    String getEmail();
    String getRole_name();
    int getCreated_at();
    int getUpdated_at();
}
