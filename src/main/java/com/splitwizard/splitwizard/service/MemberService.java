package com.splitwizard.splitwizard.service;

import com.splitwizard.splitwizard.model.Member;

import java.util.List;

public interface MemberService {

    Member getById(Integer id);
    List<Member> getAll();
    String login(String account, String password);
    String register(Member member);
}
