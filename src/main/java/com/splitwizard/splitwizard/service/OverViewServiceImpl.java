package com.splitwizard.splitwizard.service;

import com.splitwizard.splitwizard.DAO.*;
import com.splitwizard.splitwizard.POJO.Group;
import com.splitwizard.splitwizard.POJO.Member;
import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.VO.OverViewResp;
import com.splitwizard.splitwizard.VO.OverViewVO;
import com.splitwizard.splitwizard.service.intf.OverViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OverViewServiceImpl implements OverViewService {

    private final Result R;
    private final GroupRepository groupDAO;
    private final MemberRepository memberDAO;
    private final ItemDAO itemDAO;
    private final ItemDetailDAO itemDetailDAO;
    private final MemberGroupConnRepository connDAO;

    @Autowired
    OverViewServiceImpl(GroupRepository groupDAO, MemberRepository memberDAO,
                        ItemDAO itemDAO, ItemDetailDAO itemDetailDAO,
                        MemberGroupConnRepository connDAO){

        this.groupDAO = groupDAO;
        this.itemDAO = itemDAO;
        this.itemDetailDAO = itemDetailDAO;
        this.memberDAO = memberDAO;
        this.connDAO = connDAO;
        this.R = new Result();

    }

    @Override
    public Result getOverView(Integer groupId) {

        try{
            OverViewResp resp = new OverViewResp();
            List<OverViewVO> overViewVOList = new ArrayList<>();

            Group group = groupDAO.getReferenceById(groupId);
            Set<Member> members = group.getMembers();

            for (Member member : members){
                OverViewVO overViewVO = new OverViewVO();

                overViewVO.setMemberId(member.getId());
                overViewVO.setMemberName(member.getName());
                overViewVO.setMemberNet(connDAO.findByGroupIdAndMemberId(groupId, member.getId()).getNet());

                overViewVOList.add(overViewVO);
            }

            resp.setGroupName(group.getName());
            resp.setGroupArchive(group.getArchive());
            resp.setOverViews(overViewVOList);

            return R.success(resp);
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }

    @Override
    public Result getItemDetailsOfMemberInGroup(Integer groupId, Integer MemberId) {
        return null;
    }
}
