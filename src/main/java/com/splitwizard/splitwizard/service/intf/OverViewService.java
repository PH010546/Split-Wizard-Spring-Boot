package com.splitwizard.splitwizard.service.intf;

import com.splitwizard.splitwizard.Util.Result;

public interface OverViewService {

    Result getOverView(Integer groupId);

    Result getItemDetailsOfMemberInGroup(Integer groupId, Integer MemberId);

}
