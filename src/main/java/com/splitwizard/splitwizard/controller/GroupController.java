package com.splitwizard.splitwizard.controller;

import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupController {

    private final GroupService service;

    @Autowired
    public GroupController(GroupService service){

        this.service = service;

    }
    @PostMapping(value = "/group")
    public Result getGroup(Integer id){
        return service.getById(id);
    }

}
