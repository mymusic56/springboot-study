package com.mymusic.testthymeleafjpa.repository;

import com.mymusic.testthymeleafjpa.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<LogEntity, Long> {
}
