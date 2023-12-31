package com.splitwizard.splitwizard.controller;

import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.VO.req.LoginReq;
import com.splitwizard.splitwizard.VO.req.RegisterReq;
import com.splitwizard.splitwizard.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    // TODO: change the request body to VO

    private final MemberServiceImpl service;

    @Autowired
    MemberController(MemberServiceImpl service) {
        this.service = service;
    }

    @PostMapping(value = "/login")
    public Result login(@RequestBody LoginReq member) {
        return service.login(member.getAccount(), member.getPassword());
    }

    @PostMapping(value = "/register")
    public Result register(@RequestBody RegisterReq member) {
        return service.register(member);
    }

    @GetMapping(value = "/allMembers")
    public Result getAllMember() {
        return service.getAllMemberWithoutPassword();
    }

    @PostMapping(value = "memberLogout")
    public Result logout() {
        SecurityContextHolder.clearContext();
        return new Result().success("logout success");
    }

    // TODO: add a auth controller? or maybe can put it in filterChain?
    @GetMapping(value = "test-token")
    public Result testToken() {
        return new Result().success("token pass");
    }
}
