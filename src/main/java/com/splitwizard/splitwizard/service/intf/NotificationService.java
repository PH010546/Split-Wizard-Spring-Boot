package com.splitwizard.splitwizard.service.intf;

import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.VO.NotificationVO;
import com.splitwizard.splitwizard.model.Notification;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService {

    Result addNotification(NotificationVO notificationVo);
    Result getNotificationsWithLimit(Integer currentUserId);
    Result getAllNotifications(Integer currentUserId);

}
