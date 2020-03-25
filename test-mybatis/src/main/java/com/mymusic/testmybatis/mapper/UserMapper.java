package com.mymusic.testmybatis.mapper;

import com.mymusic.testmybatis.entity.UserEntity;
import com.mymusic.testmybatis.enums.UserSexEnum;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    @Select("SELECT * FROM users")
    @Results({
            @Result(property = "userSex",  column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name")
    })
    List<UserEntity> getAll();

    @Select("SELECT * FROM users WHERE id = #{id}")
    @Results({
            @Result(property = "userSex",  column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name")
    })
    UserEntity getOne(Long id);

    @Insert({"INSERT INTO users(name,nick_name,password,sex,email,created_at,role_id) " +
            "VALUES(#{name}, #{nickName}, #{password}, #{userSex}, #{email}, #{created_at}, #{role_id})"})
    @SelectKey(keyColumn = "id", statement = "select last_insert_id()",
            before = false, keyProperty = "id", resultType = Long.class)
    Long insert(UserEntity user);

    @Update("UPDATE users SET name=#{name},nick_name=#{nickName}, updated_at=#{updated_at} " +
            "WHERE id =#{id}")
    int update(UserEntity user);

    @Delete("DELETE FROM users WHERE id =#{id}")
    int delete(Long id);

}
