package com.splitwizard.splitwizard.service;

import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.model.Member;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MemberService {

    Member getById(Integer id);
    List<Member> getAll();
    Result login(String account, String password);
    Result register(Member member);
}
