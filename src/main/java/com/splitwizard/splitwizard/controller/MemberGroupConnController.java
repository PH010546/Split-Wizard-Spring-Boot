package com.splitwizard.splitwizard.controller;

import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.POJO.MemberGroupConn;
import com.splitwizard.splitwizard.service.MemberGroupConnServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberGroupConnController {

    private final MemberGroupConnServiceImpl service;
    private final HttpSession session;
    @Autowired
    public MemberGroupConnController(MemberGroupConnServiceImpl service, HttpSession session){
        this.service = service;
        this.session = session;
    }

    @PostMapping(value = "/addMemberToGroup")
    public Result addMemberToGroup(@RequestBody MemberGroupConn mgc){
        return service.addMemberToGroup((Integer) session.getAttribute("currentUser"), mgc.getGroupId());
    }

}
