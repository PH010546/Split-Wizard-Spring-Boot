package com.splitwizard.splitwizard.service.intf;

import com.splitwizard.splitwizard.Util.Result;

public interface ResultService {

    Result getResult(Integer groupId);
    Result createSettlement(Integer groupId);
    Result switchResultStatusAndUpdateNet(Integer resultId);

}
