package com.splitwizard.splitwizard.controller;

import com.splitwizard.splitwizard.Jwt.UserDetailsImpl;
import com.splitwizard.splitwizard.POJO.MemberGroupConn;
import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.service.MemberGroupConnServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberGroupConnController {

    private final MemberGroupConnServiceImpl service;
    @Autowired
    public MemberGroupConnController(MemberGroupConnServiceImpl service){
        this.service = service;
    }

    @PostMapping(value = "/addMemberToGroup")
    public Result addMemberToGroup(@RequestBody MemberGroupConn mgc){
        Integer currentUserId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();

        return service.addMemberToGroup(currentUserId, mgc.getGroupId());
    }

}
