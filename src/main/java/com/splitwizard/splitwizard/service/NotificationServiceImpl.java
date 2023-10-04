package com.splitwizard.splitwizard.service;

import com.splitwizard.splitwizard.DAO.NotificationRepository;
import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.model.Notification;
import com.splitwizard.splitwizard.service.intf.NotificationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository dao;
    private final Result R;
    private final HttpSession session;
    @Autowired
    public NotificationServiceImpl(NotificationRepository dao, HttpSession session){
        this.dao = dao;
        this.R = new Result();
        this.session = session;
    }

    @Override
    public Result addNotification(Notification notification) {
        try{

            notification.setSenderId((Integer) session.getAttribute("currentUser"));
            if (Objects.equals(notification.getReceiverId(), session.getAttribute("currentUser"))) throw new Exception("sender and receiver cannot be the same member!");
            return R.success(dao.save(notification));

        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }

    @Override
    public Result getNotificationsWithLimit(Integer currentUserId) {
        return null;
    }

    @Override
    public Result getAllNotifications(Integer currentUserId) {
        return null;
    }
}
