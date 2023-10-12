package com.splitwizard.splitwizard.service.intf;

import com.splitwizard.splitwizard.Util.Result;

public interface ResultService {

    Result getResult();
    Result createSettlement(Integer groupId);
    Result switchResultStatus(Integer resultId);

}
