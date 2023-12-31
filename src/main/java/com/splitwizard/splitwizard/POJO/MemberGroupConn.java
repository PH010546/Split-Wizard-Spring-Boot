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
@Table(name = "member_group_conn")
public class MemberGroupConn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "member_id")
    private Integer memberId;
    @Column(name = "group_id")
    private Integer groupId;
    @Column(columnDefinition = "numeric(38, 2) default 0")
    private BigDecimal net = BigDecimal.valueOf(0.0);
    @Column(name = "update_time", insertable = false, columnDefinition = "timestamptz default now()")
    private Timestamp updateTime = new Timestamp(System.currentTimeMillis());

}
