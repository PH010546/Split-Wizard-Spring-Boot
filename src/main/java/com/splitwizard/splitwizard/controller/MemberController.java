package com.splitwizard.splitwizard.controller;

import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.model.Member;
import com.splitwizard.splitwizard.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(value = "http://localhost:3000")
public class MemberController {

    private final MemberServiceImpl service;
    @Autowired
    MemberController(MemberServiceImpl service){
        this.service = service;
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result login(@RequestBody Member member){
        return service.login(member.getAccount(), member.getPassword());
    }

    @PostMapping("/register")
    public Result register(@RequestBody Member member){

        return service.register(member);
    }
}
