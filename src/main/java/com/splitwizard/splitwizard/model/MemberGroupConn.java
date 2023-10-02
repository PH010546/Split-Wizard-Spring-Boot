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
public class MemberGroupConn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "member_id")
    private Integer memberId;
    @Column(name = "group_id")
    private Integer groupId;
    private Long net;
    private Timestamp update_time;

}
