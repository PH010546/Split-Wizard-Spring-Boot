package com.splitwizard.splitwizard.controller;

import com.splitwizard.splitwizard.DTO.MemberDTO;
import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.model.Member;
import com.splitwizard.splitwizard.service.MemberServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    // TODO: change the request body to VO

    private final MemberServiceImpl service;
    @Autowired
    MemberController(MemberServiceImpl service){
        this.service = service;
    }

    @PostMapping(value = "/login")
    public Result login(@RequestBody Member member, HttpSession session){
        Result result = service.login(member.getAccount(), member.getPassword());
        MemberDTO currentUser = (MemberDTO) result.getResult();

        session.setAttribute("currentUser", currentUser.getId());

        return result;
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
