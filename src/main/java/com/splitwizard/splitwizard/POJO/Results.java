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
    private Boolean status = false;
    @Column(name = "created_time", updatable = false)
    private Timestamp createdTime = new Timestamp(System.currentTimeMillis());
    @Column(name = "update_time")
    private Timestamp updateTime = new Timestamp(System.currentTimeMillis());

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "giver_id", insertable = false, updatable = false)
    Member giver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taker_id", insertable = false, updatable = false)
    Member taker;

}
