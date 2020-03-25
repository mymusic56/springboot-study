package com.mymusic.jpatest.jpa.repository;

import com.mymusic.jpatest.jpa.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Override
    @Transactional
    <S extends User> S save(S s);
}
