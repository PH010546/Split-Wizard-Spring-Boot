package com.splitwizard.splitwizard.controller;

import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.VO.ItemDetailReq;
import com.splitwizard.splitwizard.service.ItemDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemDetailController {

    private final ItemDetailServiceImpl service;
    @Autowired
    public ItemDetailController(ItemDetailServiceImpl service){
        this.service = service;
    }
    @PostMapping("/groups/{groupId}/{itemId}/addItemDetails")
    public Result addItemDetails(@PathVariable(name = "groupId") Integer groupId,
                                 @PathVariable(name = "itemId") Integer itemId,
                                 @RequestBody ItemDetailReq req){

        return service.addItemDetails(groupId, itemId, req);
    }
}
