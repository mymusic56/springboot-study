package com.mymusic.jpatest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserV2Entity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    @ManyToOne(targetEntity = RoleEntity.class)
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    private int created_at;
    private int updated_at;


    public Date getCreatedDate (int created_at){
        return new Date(created_at);
    }

    public String toString() {
        return "name: " + this.name + ", email: " + this.email;
    }
}
