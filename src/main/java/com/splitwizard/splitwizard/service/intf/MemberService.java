package com.splitwizard.splitwizard.service.intf;

import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.model.Member;

public interface MemberService {

    Result getById(Integer id);
    Result getAll();
    Result login(String account, String password);
    Result register(Member member);
}
