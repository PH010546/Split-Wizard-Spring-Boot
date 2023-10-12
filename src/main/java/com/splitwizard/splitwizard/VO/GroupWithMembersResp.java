package com.splitwizard.splitwizard.VO;

import com.splitwizard.splitwizard.DTO.MemberDTO;
import com.splitwizard.splitwizard.POJO.Group;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter @Setter
@NoArgsConstructor
public class GroupWithMembersResp {
    // id = groupId, name = groupName

    private Integer id;
    private String name;
    private List<MemberDTO> groupMembers;

    public GroupWithMembersResp(Integer groupId, String groupName, List<MemberDTO> groupMembers){
        this.id = groupId;
        this.name = groupName;
        this.groupMembers = groupMembers;
    }

    public GroupWithMembersResp convert(Group group){

        MemberDTO memberDTO= new MemberDTO();
        return new GroupWithMembersResp(
                group.getId(),
                group.getName(),
                memberDTO.convertList(group.getMembers().stream().toList()));
    }

    public List<GroupWithMembersResp> convertList(List<Group> groups){

        List<GroupWithMembersResp> dtoList = new ArrayList<>();

        for (Group g : groups){
            dtoList.add(convert(g));
        }
        return dtoList;
    }
}
