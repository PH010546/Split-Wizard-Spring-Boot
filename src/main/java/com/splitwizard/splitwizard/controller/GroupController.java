package com.splitwizard.splitwizard.controller;

import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.model.Group;
import com.splitwizard.splitwizard.service.GroupServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GroupController {

    private final GroupServiceImpl service;

    @Autowired
    public GroupController(GroupServiceImpl service){

        this.service = service;

    }
    @GetMapping(value = "/groups")
    public Result getGroup(HttpSession session){
        System.out.println(session.getAttribute("currentUser"));
        return service.getById((Integer) session.getAttribute("currentUser"));
    }

    @PostMapping(value = "/addGroup")
    public Result addGroup(@RequestBody Group group){
        return service.addGroup(group);
    }

    @PutMapping(value = "/group/update")
    public Result updateGroupName(@RequestBody Group group){
        return service.updateGroupName(group.getName(), group.getId());
    }

    @DeleteMapping(value = "/group/delete")
    public Result deleteGroup(Integer id){
        return service.deleteGroup(id);
    }

}
