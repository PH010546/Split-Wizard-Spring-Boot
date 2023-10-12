package com.splitwizard.splitwizard.VO;

import com.splitwizard.splitwizard.DAO.GroupRepository;
import com.splitwizard.splitwizard.DAO.MemberRepository;
import com.splitwizard.splitwizard.DTO.MemberDTO;
import com.splitwizard.splitwizard.POJO.Group;
import com.splitwizard.splitwizard.POJO.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter @Setter
public class SingleItemResp {

    private String itemName;
    private String itemTime;
    private Integer amount;
    private List<ItemDetailVO> details;
    private String groupName;
    private Boolean groupArchive;
    private List<MemberDTO> groupMembers;



    public SingleItemResp convertItemToResp(Item item, Integer groupId,
                                            MemberRepository memberDAO, GroupRepository groupDAO){

        ItemDetailVO itemDetailVO = new ItemDetailVO();
        SingleItemResp resp = new SingleItemResp();
        MemberDTO memberDTO = new MemberDTO();

        Group group = groupDAO.getReferenceById(groupId);

        resp.setItemName(item.getName());
        resp.setAmount(item.getAmount());
        resp.setItemTime(item.getItemTime());
        resp.setDetails(itemDetailVO.convertPOJOListToVOList(item.getItemDetails(), memberDAO));
        resp.setGroupName(group.getName());
        resp.setGroupArchive(group.getArchive());
        // TODO: members should be Set or List? or can just let the convert method accept Collection type?
        resp.setGroupMembers(memberDTO.convertList(group.getMembers().stream().toList()));

        return resp;

    }

}
