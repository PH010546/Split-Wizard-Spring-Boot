package com.splitwizard.splitwizard.POJO;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
@Entity
@Data
@NoArgsConstructor
@Getter @Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private Integer id;
    private String name;
    private Integer amount;
    private String itemTime;
    @Column(name = "created_time", insertable = false, updatable = false)
    private Timestamp createdTime;
    @Column(name = "update_time", insertable = false)
    private Timestamp updateTime;
    @Column(name = "group_id")
    private Integer groupId;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "item_fk"), insertable = false, updatable = false)
    Group group;

}