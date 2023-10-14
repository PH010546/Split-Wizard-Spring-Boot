package com.splitwizard.splitwizard.controller;

import com.splitwizard.splitwizard.DTO.ResultDTO;
import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.service.ResultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/groups/{groupId}/getResult")
    public Result getResult(@PathVariable(name = "groupId") Integer groupId){
        return service.getResult(groupId);
    }
    @PutMapping("/groups/*/switchResultStatus")
    public Result switchResultStatus(@RequestBody ResultDTO resultDTO){
        return service.switchResultStatusAndUpdateNet(resultDTO.getId());
    }
}
