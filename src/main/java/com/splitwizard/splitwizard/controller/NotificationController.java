package com.splitwizard.splitwizard.controller;

import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.model.Notification;
import com.splitwizard.splitwizard.service.NotificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Result addNotification(@RequestBody Notification notification){
        return service.addNotification(notification);
    }

}
