package com.splitwizard.splitwizard.service.intf;

import com.splitwizard.splitwizard.Util.Result;
import org.springframework.stereotype.Service;

@Service
public interface MemberGroupConnService {

    Result addMemberToGroup(Integer memberId, Integer groupId);

}
