package com.splitwizard.splitwizard.service;

import com.splitwizard.splitwizard.DAO.GroupRepository;
import com.splitwizard.splitwizard.DTO.GroupDTO;
import com.splitwizard.splitwizard.DTO.MemberDTO;
import com.splitwizard.splitwizard.POJO.Group;
import com.splitwizard.splitwizard.POJO.Member;
import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.VO.resp.GroupWithMembersResp;
import com.splitwizard.splitwizard.service.intf.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository dao;
    private final Result R;
    private final GroupDTO groupDTO;
    private final GroupWithMembersResp resp;
    @Autowired
    public GroupServiceImpl(GroupRepository dao){
        this.dao = dao;
        this.R = new Result();
        this.groupDTO = new GroupDTO();
        this.resp = new GroupWithMembersResp();
    }

    @Override
    public Result addGroup(Group group) {

        try{

            // TODO: need to validate?
            // save & return DTO
            return R.success(groupDTO.convert(dao.save(group)));

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
        group.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        return R.success(groupDTO.convert(dao.save(group)));
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
            // TODO: need to modify
            List<Group> groupList = dao.findAll();
            List<Group> resultList = new ArrayList<>() ;

            for (Group g : groupList){
                for (Member m : g.getMembers()){
                    if (Objects.equals(m.getId(), currentUserId)){
                        resultList.add(g);
                    }
                }
            }
            return R.success(resp.convertList(resultList));
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }

    @Override
    public Result resetRedirect(Integer groupId) {

        try{
            Group group = dao.getReferenceById(groupId);
            group.setRedirect(false);
            group.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            dao.save(group);
            return R.success(null);
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }

    @Override
    public Result getMembersInGroup(Integer groupId) {

        try{
            MemberDTO memberDTO = new MemberDTO();

            Group group = dao.getReferenceById(groupId);

            Set<Member> set = group.getMembers();
            List<MemberDTO> members = new ArrayList<>();

            for (Member m : set){
                members.add(memberDTO.convert(m));
            }

            return R.success(members);
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }
}
