package com.splitwizard.splitwizard.service;

import com.splitwizard.splitwizard.DAO.GroupRepository;
import com.splitwizard.splitwizard.DAO.ItemDetailDAO;
import com.splitwizard.splitwizard.DAO.MemberGroupConnRepository;
import com.splitwizard.splitwizard.DAO.ResultDAO;
import com.splitwizard.splitwizard.POJO.Group;
import com.splitwizard.splitwizard.POJO.ItemDetail;
import com.splitwizard.splitwizard.POJO.Member;
import com.splitwizard.splitwizard.POJO.Results;
import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.VO.OverViewVO;
import com.splitwizard.splitwizard.VO.resp.OverViewDetailsResp;
import com.splitwizard.splitwizard.VO.resp.OverViewResp;
import com.splitwizard.splitwizard.service.intf.OverViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class OverViewServiceImpl implements OverViewService {

    private final Result R;
    private final GroupRepository groupDAO;
    private final MemberGroupConnRepository connDAO;
    private final ItemDetailDAO itemDetailDAO;
    private final ResultDAO resultDAO;

    @Autowired
    OverViewServiceImpl(GroupRepository groupDAO,
                        MemberGroupConnRepository connDAO,
                        ItemDetailDAO itemDetailDAO,
                        ResultDAO resultDAO){

        this.groupDAO = groupDAO;
        this.connDAO = connDAO;
        this.itemDetailDAO = itemDetailDAO;
        this.resultDAO = resultDAO;
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
    public Result getItemDetailsOfMemberInGroup(Integer groupId, Integer memberId) {

        try{
            // details
            Group group = groupDAO.getReferenceById(groupId);
            List<ItemDetail> details = new ArrayList<>();

            // add the member's details to List.
            group.getItems().forEach(item -> details.addAll(item.getItemDetails().stream()
                    .filter(itemDetail -> Objects.equals(itemDetail.getMemberId(), memberId))
                    .toList()));

            OverViewDetailsResp resp = new OverViewDetailsResp();

            resp.convertAndSetDetails(details);


            // results
            List<Results> resultList = resultDAO.findAllByGroupId(groupId);

            resp.convertAndSetResults(resultList);

            return R.success(resp);

        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }
}
