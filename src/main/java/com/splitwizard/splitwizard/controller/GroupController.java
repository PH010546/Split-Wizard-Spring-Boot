package com.splitwizard.splitwizard.controller;

import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.model.Group;
import com.splitwizard.splitwizard.service.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupController {

    private final GroupServiceImpl service;

    @Autowired
    public GroupController(GroupServiceImpl service){

        this.service = service;

    }
    @PostMapping(value = "/group")
    public Result getGroup(Integer id){
        return service.getById(id);
    }

    @PostMapping(value = "/group/add")
    public Result addGroup(@RequestBody Group group){
        return service.addGroup(group);
    }

    @PostMapping(value = "group/update")
    public Result updateGroupName(@RequestBody Group group){
        return service.updateGroupName(group.getName(), group.getId());
    }

}
