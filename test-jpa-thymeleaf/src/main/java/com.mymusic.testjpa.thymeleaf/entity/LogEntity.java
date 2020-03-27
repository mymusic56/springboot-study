package com.mymusic.testjpa.thymeleaf.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "log")
public class LogEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String action;

    private int created_at;

    @Column(columnDefinition = "text")
    private String request_param;

    private long user_id;
}
