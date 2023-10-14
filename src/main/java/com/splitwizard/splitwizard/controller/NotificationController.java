package com.splitwizard.splitwizard.controller;

import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.VO.NotificationVO;
import com.splitwizard.splitwizard.service.NotificationServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    private final NotificationServiceImpl service;
    @Autowired
    NotificationController(NotificationServiceImpl service){
        this.service = service;
    }
    @PostMapping("/addNotifications")
    public Result addNotification(@RequestBody NotificationVO notificationVO){
        return service.addNotifications(notificationVO);
    }

    @GetMapping("/getNotifications")
    public Result getNotificationsWithLimit(HttpSession session){
        return service.getNotificationsWithLimit((Integer) session.getAttribute("currentUser"));
    }
    @PostMapping("/readNotification")
    public Result readNotification(@RequestBody NotificationVO notificationVO){
        return service.readNotification(notificationVO.getId());
    }
    @GetMapping("/getAllNotifications")
    public Result getAllNotifications(HttpSession session){
        return service.getAllNotifications((Integer) session.getAttribute("currentUser"));
    }

}
