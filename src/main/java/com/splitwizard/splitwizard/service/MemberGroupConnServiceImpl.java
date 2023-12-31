package com.splitwizard.splitwizard.service;

import com.splitwizard.splitwizard.DAO.MemberGroupConnRepository;
import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.POJO.MemberGroupConn;
import com.splitwizard.splitwizard.service.intf.MemberGroupConnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class MemberGroupConnServiceImpl implements MemberGroupConnService {

    private final MemberGroupConnRepository dao;
    private final Result R;
    @Autowired
    public MemberGroupConnServiceImpl(MemberGroupConnRepository dao){
        this.dao = dao;
        this.R = new Result();
    }

    @Override
    public Result addMemberToGroup(Integer memberId, Integer groupId) {

        try{
            MemberGroupConn mgc = new MemberGroupConn();

            mgc.setMemberId(memberId);
            mgc.setGroupId(groupId);
            mgc.setUpdateTime(new Timestamp(System.currentTimeMillis()));

            dao.save(mgc);

            return R.success(null);
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }
}
