package com.mymusic.jpatest.repository;

import com.mymusic.jpatest.dao.UserAndRoleDao;
import com.mymusic.jpatest.dto.UserAndRoleDto;
import com.mymusic.jpatest.entity.UserEntity;
import com.mymusic.jpatest.entity.UserV2Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UserV2Repository extends JpaRepository<UserV2Entity, Long> {

    @Query("select u.id,u.name,u.email,u.created_at,u.updated_at,r.role_name from UserV2Entity u " +
            "left join RoleEntity r on(u.role = r.id) where u.id = ?1")
    Object[] getUserInfoV1(Long id);

    @Query(value = "select u.id user_id,u.name,u.email,u.created_at,u.updated_at,u.role_id," +
            "r.role_name " +
            "from users u " +
            "left join role r on(u.role_id = r.id) where u.id = ?1 limit 1", nativeQuery = true)
    UserAndRoleDao getUserInfoV2(Long id);
}
