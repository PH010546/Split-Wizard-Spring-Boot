package com.splitwizard.splitwizard.service.intf;

import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.POJO.Group;
import org.springframework.stereotype.Service;

@Service
public interface GroupService {

    Result addGroup(Group group);
    Result updateGroupName(String name, Integer id);
    Result deleteGroup(Integer id);
    Result findAllCurrentGroupsWithMembers(Integer memberId);
    Result resetRedirectAndCleanUnpayedResult(Integer groupId);
    Result getMembersInGroup(Integer groupId);

}
