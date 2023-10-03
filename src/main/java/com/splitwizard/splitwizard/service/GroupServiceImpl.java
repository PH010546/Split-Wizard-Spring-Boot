package com.splitwizard.splitwizard.service;

import com.splitwizard.splitwizard.DAO.GroupRepository;
import com.splitwizard.splitwizard.Util.GroupDTO;
import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.model.Group;
import com.splitwizard.splitwizard.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    @Override
    public Result updateGroupName(String name, Integer id) {

        Optional<Group> groupOptional = dao.findById(id);

        if (groupOptional.isEmpty()) return R.fail("group not found with id");

        Group group = groupOptional.get();

        group.setName(name);
        group.setUpdate_time(new Timestamp(System.currentTimeMillis()));

        return R.success(dto.convert(dao.save(group)));
    }

    @Override
    public Result deleteGroup(Integer id) {

        try{
            dao.deleteById(id);
            return R.success(id);
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }

    @Override
    public Result findAllCurrentGroupsWithMembers(Integer currentUserId) {
        try{

            List<Group> groupList = dao.findAll();
            List<Group> resultList = new ArrayList<>() ;

            for (Group g : groupList){
                for (Member m : g.getMembers()){
                    if (Objects.equals(m.getId(), currentUserId)){
                        resultList.add(g);
                    }
                }
            }
            return R.success(resultList);
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }
}
