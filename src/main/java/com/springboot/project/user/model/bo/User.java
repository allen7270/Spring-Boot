package com.springboot.project.user.model.bo;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity // create table
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2", parameters = {@org.hibernate.annotations.Parameter(name = "uuid_gen_strategy_class", value = "org.hibernate.id.uuid.CustomVersionOneStrategy")})
    private String id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
}
