package com.splitwizard.splitwizard.controller;

import com.splitwizard.splitwizard.model.Member;
import com.splitwizard.splitwizard.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/login")
    public String login(@RequestBody Member member){
        return service.login(member.getAccount(), member.getPassword());
    }

    @PostMapping("/register")
    public String register(@RequestBody Member member){

        return service.register(member);
    }
}
