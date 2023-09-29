package com.splitwizard.splitwizard.service;

import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.model.Group;
import org.springframework.stereotype.Service;

@Service
public interface GroupService {

    Result getById(Integer id);

    Result addGroup(Group group);

}
