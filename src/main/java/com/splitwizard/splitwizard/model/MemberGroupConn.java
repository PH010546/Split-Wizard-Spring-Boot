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
    private Integer member_id;
    private Integer group_id;
    private Long net;
    private Timestamp update_time;

}
