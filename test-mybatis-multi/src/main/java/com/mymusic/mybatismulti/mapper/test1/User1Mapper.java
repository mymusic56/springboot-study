package com.mymusic.mybatismulti.mapper.test1;

import com.mymusic.mybatismulti.entity.UserEntity;
import com.mymusic.mybatismulti.enums.UserSexEnum;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface User1Mapper {


	@Select("SELECT * FROM users")
	@Results({
			@Result(property = "userSex",  column = "sex", javaType = UserSexEnum.class),
			@Result(property = "nickName", column = "nick_name")
	})
	List<UserEntity> getAll();

	@Select("SELECT * FROM users WHERE id = #{id}")
	@Results({
			@Result(property = "userSex",  column = "sex", javaType = UserSexEnum.class),
			@Result(property = "nickName", column = "nick_name")
	})
	UserEntity getOne(Long id);

	@Insert("INSERT INTO users(name,nick_name,password,sex,created_at) VALUES" +
			"(#{name}, #{nickName}, #{password}, #{userSex}, #{created_at})")
	void insert(UserEntity user);

	@Update("UPDATE users SET name=#{userName},nick_name=#{nickName} WHERE id =#{id}")
	void update(UserEntity user);

	@Delete("DELETE FROM users WHERE id =#{id}")
	void delete(Long id);

}