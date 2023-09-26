package com.splitwizard.splitwizard.controller;

import com.splitwizard.splitwizard.model.Member;
import com.splitwizard.splitwizard.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    private final MemberService service;
    @Autowired
    MemberController(MemberService service){
        this.service = service;
    }

    @PostMapping("/login")
    public String login(@RequestBody Member member){
        return service.login(member.getAccount(), member.getPassword());
    }


}
