package com.splitwizard.splitwizard.Util;

import com.splitwizard.splitwizard.model.Group;
import com.splitwizard.splitwizard.model.Notification;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter @Setter
public class NotificationDTO {

    private Integer id;
    private String text;
    private Boolean read;
    private Timestamp createdTime;
    private Integer type;
    private Integer groupId;

    public NotificationDTO(Integer id, String text, Boolean read, Timestamp createdTime, Integer type, Integer groupId){
        this.id = id;
        this.text = text;
        this.read = read;
        this.createdTime = createdTime;
        this.type = type;
        this.groupId = groupId;
    }

    public NotificationDTO convert(Notification notification){

        Group group = notification.getGroup();

        if (group == null) return new NotificationDTO(
                notification.getId(),
                notification.getText(),
                notification.getRead(),
                notification.getCreatedTime(),
                notification.getType(),
                null);
        else {
            return new NotificationDTO(
                    notification.getId(),
                    notification.getText(),
                    notification.getRead(),
                    notification.getCreatedTime(),
                    notification.getType(),
                    notification.getGroup().getId());
        }
    }

    public List<NotificationDTO> convertList(List<Notification> notifications){

        List<NotificationDTO> result = new ArrayList<>();

        for (Notification notification : notifications){
            result.add(convert(notification));
        }
        return result;
    }

}
