package com.splitwizard.splitwizard.controller;

import com.splitwizard.splitwizard.POJO.Member;
import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    MemberController(MemberServiceImpl service){
        this.service = service;
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/login")
    public Result login(@RequestBody Member member){
        Result result = service.login(member.getAccount(), member.getPassword());
//        MemberDTO currentUser = (MemberDTO) result.getResult();

        Authentication authAfterSuccessLogin = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(member.getAccount(), member.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authAfterSuccessLogin);

//        session.setAttribute("currentUser", currentUser.getId());

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
