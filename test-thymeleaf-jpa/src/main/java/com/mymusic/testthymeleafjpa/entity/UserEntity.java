package com.mymusic.testthymeleafjpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    private int role_id;

    private int created_at;
    private int updated_at;


    public Date getCreatedDate (int created_at){
        return new Date(((long)created_at)*1000);
    }

    public String toString() {
        return "name: " + this.name + ", email: " + this.email;
    }
}
