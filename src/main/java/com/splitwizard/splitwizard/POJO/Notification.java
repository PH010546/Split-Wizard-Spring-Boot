package com.splitwizard.splitwizard.POJO;

import com.splitwizard.splitwizard.Util.NotificationType;
import com.splitwizard.splitwizard.Util.NotificationTypeConverter;
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
    @Column(name = "created_time", updatable = false)
    private Timestamp createdTime = new Timestamp(System.currentTimeMillis());
    private Boolean read = false;
    @Convert(converter = NotificationTypeConverter.class)
    private NotificationType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "notification_group_id_fk"))
    Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", foreignKey = @ForeignKey(name = "notification_fk_1"))
    Member sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", foreignKey = @ForeignKey(name = "notification_fk_2"))
    Member receiver;

}
