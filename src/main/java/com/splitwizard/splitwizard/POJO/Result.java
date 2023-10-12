package com.splitwizard.splitwizard.POJO;

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
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "group_id")
    private Integer groupId;
    @Column(name = "ower_id")
    private Integer owerId;
    @Column(name = "payer_id")
    private Integer payerId;
    private Long amount;
    @Column(insertable = false)
    private Boolean status;
    @Column(name = "created_time", insertable = false, updatable = false)
    private Timestamp createdTime;
    @Column(name = "update_time", insertable = false)
    private Timestamp updateTime;

}
