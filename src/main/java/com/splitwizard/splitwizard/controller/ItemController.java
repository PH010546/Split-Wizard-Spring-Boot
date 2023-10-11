package com.splitwizard.splitwizard.controller;

import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.VO.ItemVO;
import com.splitwizard.splitwizard.service.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ItemController {

    private final ItemServiceImpl service;
    @Autowired
    public ItemController(ItemServiceImpl service){
        this.service = service;
    }
    @PostMapping("/groups/{groupId}/addItem")
    public Result addItem(@PathVariable Integer groupId, @RequestBody ItemVO itemVO){
        return service.addItem(itemVO, groupId);
    }
    @GetMapping("/groups/{groupId}")
    public Result getItemsInGroup(@PathVariable Integer groupId){
        return service.getItemsInGroup(groupId);
    }
    @GetMapping("/groups/*/{itemId}")
    public Result getSingleItem(@PathVariable(name = "itemId") Integer itemId){
        return service.getSingleItem(itemId);
    }
}
