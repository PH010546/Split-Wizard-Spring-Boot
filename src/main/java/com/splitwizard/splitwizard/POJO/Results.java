package com.splitwizard.splitwizard.POJO;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Getter @Setter
@Table(name = "Result")
public class Results {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "group_id")
    private Integer groupId;
    @Column(name = "giver_id")
    private Integer giverId;
    @Column(name = "taker_id")
    private Integer takerId;
    private BigDecimal amount;
    @Column(insertable = false, columnDefinition = "boolean default false")
    private Boolean status;
    @Column(name = "created_time",insertable = false, updatable = false, columnDefinition = "timestamptz default now()")
    private Timestamp createdTime;
    @Column(name = "update_time", insertable = false, columnDefinition = "timestamptz default now()")
    private Timestamp updateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "giver_id", insertable = false, updatable = false)
    Member giver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taker_id", insertable = false, updatable = false)
    Member taker;

}
