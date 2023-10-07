package com.splitwizard.splitwizard.service;

import com.splitwizard.splitwizard.DAO.GroupRepository;
import com.splitwizard.splitwizard.DAO.MemberRepository;
import com.splitwizard.splitwizard.DAO.NotificationRepository;
import com.splitwizard.splitwizard.DTO.NotificationDTO;
import com.splitwizard.splitwizard.Util.NotificationType;
import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.VO.NotificationVO;
import com.splitwizard.splitwizard.model.Notification;
import com.splitwizard.splitwizard.service.intf.NotificationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.splitwizard.splitwizard.Util.NotificationType.*;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository dao;
    private final GroupRepository groupDAO;
    private final MemberRepository memberDAO;
    private final Result R;
    private final HttpSession session;
    private final NotificationDTO dto;
    @Autowired
    public NotificationServiceImpl(NotificationRepository dao, HttpSession session,
                                   GroupRepository groupDAO, MemberRepository memberDAO){
        this.dao = dao;
        this.R = new Result();
        this.session = session;
        this.dto = new NotificationDTO();
        this.groupDAO = groupDAO;
        this.memberDAO = memberDAO;

    }

    @Override
    public Result addNotification(NotificationVO notificationVO) {
        try{



            notificationVO.setSenderId((Integer) session.getAttribute("currentUser"));
            if (Objects.equals(notificationVO.getReceiverId(), session.getAttribute("currentUser"))) throw new Exception("sender and receiver cannot be the same member!");

            Notification savedPOJO = dao.save(notificationVO.convertVOToDTO(groupDAO, memberDAO).convertDTOToPOJO(groupDAO, memberDAO));

            return R.success(dto.convertPOJOToDTO(savedPOJO).convertDTOToVO());
//            return R.success(dao.save(notificationVO.convertVOToDTO(groupDAO, memberDAO).convertDTOToPOJO(groupDAO, memberDAO)));

        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }

    @Override
    public Result getNotificationsWithLimit(Integer currentUserId) {

        try{
            Page<Notification> page = dao.findNotificationsByReceiverId(currentUserId,
                    PageRequest.of(0, 8, Sort.by(Sort.Direction.DESC, "createdTime")));

            List<NotificationDTO> dtoList = dto.convertList(page.getContent());

            for (NotificationDTO d : dtoList){

                switch (NotificationType.of(d.getType().getCode())) {
                    case SYSTEM -> d.setText(SYSTEM.getText());
                    case INVITATION -> d.setText(INVITATION.getText() + d.getGroup().getName());
                    case ITEM -> d.setText("於 " + d.getGroup().getName() + " 中有" + ITEM.getText());
                    default -> {}
                }
            }

            NotificationVO vo = new NotificationVO();

            return R.success(vo.convertListDTOsToVOs(dtoList));

        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }

    }

    @Override
    public Result getAllNotifications(Integer currentUserId) {

        try{

            return R.success(dao.findAllByReceiverId(currentUserId));

        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }
}
