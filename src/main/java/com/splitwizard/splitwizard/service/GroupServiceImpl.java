package com.splitwizard.splitwizard.service;

import com.splitwizard.splitwizard.DAO.GroupRepository;
import com.splitwizard.splitwizard.Util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService{

    private final GroupRepository dao;
    private final Result R;
    @Autowired
    public GroupServiceImpl(GroupRepository dao){
        this.dao = dao;
        this.R = new Result();
    }


    @Override
    public Result getById(Integer id) {

        try{

            return R.success(dao.findById(id));

        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }
}
