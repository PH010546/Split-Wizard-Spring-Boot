package com.splitwizard.splitwizard.VO;

import com.splitwizard.splitwizard.DAO.GroupRepository;
import com.splitwizard.splitwizard.DAO.MemberRepository;
import com.splitwizard.splitwizard.DTO.GroupDTO;
import com.splitwizard.splitwizard.DTO.MemberDTO;
import com.splitwizard.splitwizard.DTO.NotificationDTO;
import com.splitwizard.splitwizard.Util.NotificationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class NotificationVO {

    private Integer id;
    private String text;
    private Boolean read;
    private Timestamp createdTime;
    private NotificationType type;
    private Integer groupId;
    private Integer senderId;
    private Integer receiverId;
    private Integer[] receiverIds;

    public NotificationVO convertDTOToVO(NotificationDTO notificationDTO){

        NotificationVO vo = new NotificationVO();

        vo.setId(notificationDTO.getId());
        vo.setText(notificationDTO.getText());
        vo.setRead(notificationDTO.getRead());
        vo.setCreatedTime(notificationDTO.getCreatedTime());
        vo.setType(notificationDTO.getType());
        vo.setGroupId(null);
        vo.setSenderId(null);
        vo.setReceiverId(null);
        if (notificationDTO.getGroup() != null) vo.setGroupId(notificationDTO.getGroup().getId());
        if (notificationDTO.getSender() != null) vo.setSenderId(notificationDTO.getSender().getId());
        if (notificationDTO.getReceiver() != null) vo.setReceiverId(notificationDTO.getReceiver().getId());

        return vo;
    }

    public List<NotificationVO> convertListDTOsToVOs(List<NotificationDTO> notificationDTOS){

        List<NotificationVO> vos = new ArrayList<>();

        for (NotificationDTO n : notificationDTOS){
            vos.add(convertDTOToVO(n));
        }

        return vos;

    }

    public NotificationDTO convertVOToDTO(GroupRepository groupDAO, MemberRepository memberDAO){
        NotificationDTO result = new NotificationDTO();
        GroupDTO groupDTO = new GroupDTO();
        MemberDTO memberDTO = new MemberDTO();

        result.setId(this.id);
        result.setText(this.text);
        result.setRead(this.read);
        result.setCreatedTime(this.createdTime);
        result.setType(this.type);
        result.setGroup(null);
        result.setSender(null);
        result.setReceiver(null);
        if (this.getGroupId() != null) result.setGroup(groupDTO.convert(groupDAO.getReferenceById(this.groupId)));
        if (this.getSenderId() != null) result.setSender(memberDTO.convert(memberDAO.getReferenceById(this.senderId)));
        if (this.getReceiverId() != null) result.setReceiver(memberDTO.convert(memberDAO.getReferenceById(this.receiverId)));

        return result;
    }

}
