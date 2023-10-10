package com.splitwizard.splitwizard.service.intf;

import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.VO.NotificationVO;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService {

    Result addNotifications(NotificationVO notificationVo);
    Result getNotificationsWithLimit(Integer currentUserId);
    Result getAllNotifications(Integer currentUserId);

}
