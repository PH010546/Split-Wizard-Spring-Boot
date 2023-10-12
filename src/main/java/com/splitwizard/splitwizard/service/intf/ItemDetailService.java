package com.splitwizard.splitwizard.service.intf;

import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.VO.ItemDetailReq;

public interface ItemDetailService {

    Result addItemDetails(Integer groupId, Integer itemId, ItemDetailReq req);
    Result editItemDetails(Integer groupId, Integer itemId, ItemDetailReq req);
}
