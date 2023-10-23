package com.splitwizard.splitwizard.controller;

import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.service.OverViewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OverViewController {

    private OverViewServiceImpl service;
    @Autowired
    OverViewController(OverViewServiceImpl service){
        this.service = service;
    }
    @GetMapping("/groups/{groupId}/overView")
    public Result getOverView(@PathVariable(name = "groupId") Integer groupId){
        return service.getOverView(groupId);
    }

    @GetMapping("/groups/{groupId}/{memberId}/details")
    public Result getMemberDetailsInGroup(@PathVariable(name = "groupId") Integer groupId,
                                          @PathVariable(name = "memberId") Integer memberId){
        return service.getItemDetailsOfMemberInGroup(groupId, memberId);
    }
}
