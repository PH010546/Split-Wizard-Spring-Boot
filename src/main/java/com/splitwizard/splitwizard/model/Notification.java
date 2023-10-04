package com.splitwizard.splitwizard.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Getter @Setter
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String text;
    @Column(name = "created_time", insertable = false)
    private Timestamp createdTime;
    @Column(insertable = false)
    private Boolean read;
    private Integer type;
    @Column(name = "group_id")
    private Integer groupId;
    @Column(name = "sender_id")
    private Integer senderId;
    @Column(name = "receiver_id")
    private Integer receiverId;


    @OneToOne
    @JoinTable(name = "group")
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    Group group;

}
