package com.splitwizard.splitwizard.service.intf;

import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.VO.ItemVO;
import org.springframework.stereotype.Service;

@Service
public interface ItemService {

    Result addItem(ItemVO itemVO, Integer groupId);
    Result getItemsInGroup(Integer groupId);
    Result getSingleItem(Integer itemId);
    Result editItem(ItemVO itemVO);
    Result deleteItem(Integer itemId);

}
