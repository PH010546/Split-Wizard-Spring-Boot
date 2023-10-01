package com.splitwizard.splitwizard.controller;

import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.model.Member;
import com.splitwizard.splitwizard.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    private final MemberServiceImpl service;
    @Autowired
    MemberController(MemberServiceImpl service){
        this.service = service;
    }

    @PostMapping(value = "/login")
    public Result login(@RequestBody Member member){
        return service.login(member.getAccount(), member.getPassword());
    }

    @PostMapping(value = "/register")
    public Result register(@RequestBody Member member){

        return service.register(member);
    }

    @GetMapping(value = "/allMembers")
    public Result getAllMember(){
        return service.getAllMemberWithoutPassword();
    }
}
