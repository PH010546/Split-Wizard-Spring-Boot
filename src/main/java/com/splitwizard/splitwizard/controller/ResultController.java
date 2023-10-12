package com.splitwizard.splitwizard.controller;

import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.service.ResultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResultController {

    private final ResultServiceImpl service;
    @Autowired
    ResultController(ResultServiceImpl service){
        this.service = service;
    }
    @PostMapping("/groups/{groupId}/createSettlements")
    public Result createSettlements(@PathVariable(name = "groupId") Integer groupId){
        return service.createSettlement(groupId);
    }
}
