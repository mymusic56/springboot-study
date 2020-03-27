package com.mymusic.testjpa.thymeleaf.repository;

import com.mymusic.testjpa.thymeleaf.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<LogEntity, Long> {
}
