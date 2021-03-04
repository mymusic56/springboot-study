package com.mymusic.testthymeleafjpa.repository;

import com.mymusic.testthymeleafjpa.dto.UserAndRoleDto;
import com.mymusic.testthymeleafjpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "select id,`name`,email,updated_at,created_at,role_id from users where id = ?1", nativeQuery = true)
    UserEntity getById(Long id);

    @Modifying
    @Transactional
    @Query(value = "delete from users where id= ?1", nativeQuery = true)
    void deleteByUserId(Long id);

//    @Query("select u.name,u.id as user_id,u.email,r.role_name from UserEntity u " +
//            "left join RoleEntity r on(u.role_id = r.id) where u.id = ?1")
//    UserAndRoleDto getUserInfo(Long id);


    @Query("select new com.mymusic.testjpa.thymeleaf.dto.UserAndRoleDto(u,r) from UserEntity u " +
            "left join RoleEntity r on(u.role_id = r.id) where u.id = ?1")
    UserAndRoleDto getUserInfo(Long id);
}
