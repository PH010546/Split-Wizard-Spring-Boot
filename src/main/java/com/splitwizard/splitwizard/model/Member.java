package com.splitwizard.splitwizard.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String account;
    private String name;
    private String password;
    private Timestamp created_time;
    private Timestamp update_time;

    public Member(String account, String name, String password){

        this.account = account;
        this.name = name;
        this.password = password;

    }

}
