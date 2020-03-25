package com.mymusic.jpatest.repository;

import com.mymusic.jpatest.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<LogEntity, Long> {
}
