package com.splitwizard.splitwizard.service;

import com.splitwizard.splitwizard.DAO.GroupRepository;
import com.splitwizard.splitwizard.Util.GroupDTO;
import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class GroupServiceImpl implements GroupService{

    private final GroupRepository dao;
    private final Result R;
    private final GroupDTO dto;
    @Autowired
    public GroupServiceImpl(GroupRepository dao){
        this.dao = dao;
        this.R = new Result();
        this.dto = new GroupDTO();
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

    @Override
    public Result addGroup(Group group) {

        try{


            // TODO: need to validate?
            // set time
            group.setCreated_time(new Timestamp(System.currentTimeMillis()));
            group.setUpdate_time(new Timestamp(System.currentTimeMillis()));

            // set default values
            group.setArchive(false);
            group.setRedirect(false);
            group.setStatus(false);

            // save & return DTO
            return R.success(dto.convert(dao.save(group)));

        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }



    }
}
