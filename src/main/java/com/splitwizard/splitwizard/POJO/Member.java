package com.splitwizard.splitwizard.POJO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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

    @Email
    private String account;
    private String name;
    private String password;
    @Column(name = "created_time",insertable = false, updatable = false,
            columnDefinition = "timestamptz default now()")
    private Timestamp createdTime;
    @Column(name = "update_time", insertable = false,
            columnDefinition = "timestamptz default now()")
    private Timestamp updateTime;
    private String UID;

    @JsonIgnore
    private String authority = "user";

}
