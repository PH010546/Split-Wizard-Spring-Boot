package com.splitwizard.splitwizard.DTO;

import com.splitwizard.splitwizard.DAO.GroupRepository;
import com.splitwizard.splitwizard.DAO.MemberRepository;
import com.splitwizard.splitwizard.Util.NotificationType;
import com.splitwizard.splitwizard.VO.NotificationVO;
import com.splitwizard.splitwizard.model.Notification;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter @Setter
@Component
public class NotificationDTO {

    private Integer id;
    private String text;
    private Boolean read;
    private Timestamp createdTime;
    private NotificationType type;
    private GroupDTO group;
    private MemberDTO sender;
    private MemberDTO receiver;
    public NotificationDTO(Integer id, String text, Boolean read, Timestamp createdTime, NotificationType type,
                           GroupDTO group, MemberDTO sender, MemberDTO receiver){
        this.id = id;
        this.text = text;
        this.read = read;
        this.createdTime = createdTime;
        this.type = type;
        this.group = group;
        this.sender = sender;
        this.receiver = receiver;
    }

    public NotificationDTO convertPOJOToDTO(Notification notification) {

        NotificationDTO result = new NotificationDTO();
        GroupDTO groupDTO = new GroupDTO();
        MemberDTO memberDTO = new MemberDTO();

        result.setId(notification.getId());
        result.setText(notification.getText());
        result.setRead(notification.getRead());
        result.setCreatedTime(notification.getCreatedTime());
        result.setType(notification.getType());
        result.setGroup(null);
        result.setSender(null);
        result.setReceiver(null);
        if (notification.getGroup() != null) result.setGroup(groupDTO.convert(notification.getGroup()));
        if (notification.getSender() != null) result.setSender(memberDTO.convert(notification.getSender()));
        if (notification.getReceiver() != null) result.setSender(memberDTO.convert(notification.getReceiver()));

        return result;
    }


    public List<NotificationDTO> convertList(List<Notification> notifications){

        List<NotificationDTO> result = new ArrayList<>();

        for (Notification notification : notifications){
            result.add(convertPOJOToDTO(notification));
        }
        return result;
    }

    public Notification convertDTOToPOJO(GroupRepository groupDAO, MemberRepository memberDAO){
        Notification result = new Notification();

        result.setId(this.getId());
        result.setText(this.getText());
        result.setCreatedTime(this.getCreatedTime());
        result.setRead(this.getRead());
        result.setType(this.getType());
        result.setGroup(null);
        result.setSender(null);
        result.setReceiver(null);
        if (this.getGroup() != null) result.setGroup(this.getGroup().convertDTOToPOJO(groupDAO));
        if (this.getSender() != null) result.setSender(this.getSender().convertDTOToPOJO(memberDAO));
        if (this.getReceiver() != null) result.setReceiver(this.getReceiver().convertDTOToPOJO(memberDAO));

        return result;
    }

    public NotificationVO convertDTOToVO(){
        NotificationVO result = new NotificationVO();

        result.setId(this.id);
        result.setText(this.text);
        result.setRead(this.read);
        result.setCreatedTime(this.createdTime);
        result.setType(this.type);
        result.setGroupId(null);
        result.setSenderId(null);
        result.setReceiverId(null);
        if (this.group != null) result.setGroupId(this.group.getId());
        if (this.sender != null) result.setSenderId(this.sender.getId());
        if (this.receiver != null) result.setSenderId(this.receiver.getId());

        return result;
    }

}
