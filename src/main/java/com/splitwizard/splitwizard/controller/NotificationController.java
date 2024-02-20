package com.splitwizard.splitwizard.controller;

import com.splitwizard.splitwizard.Jwt.UserDetailsImpl;
import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.VO.NotificationVO;
import com.splitwizard.splitwizard.service.NotificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    public Result getNotificationsWithLimit(){
        Integer currentUserId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return service.getNotificationsWithLimit(currentUserId);
    }
    @PostMapping("/readNotification")
    public Result readNotification(@RequestBody NotificationVO notificationVO){
        return service.readNotification(notificationVO.getId());
    }
    @GetMapping("/getAllNotifications")
    public Result getAllNotifications(){
        Integer currentUserId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return service.getAllNotifications(currentUserId);
    }

    @DeleteMapping("/deleteNotification")
    public Result deleteNotification(@RequestBody NotificationVO notificationVO){
        return service.deleteNotification(notificationVO.getId());
    }

}
