package com.splitwizard.splitwizard.Util;

import com.splitwizard.splitwizard.model.Group;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class MemberGroupConnDTO {

    private Integer groupId;
    private String groupName;
    private List<MemberDTO> groupMembers;

    public MemberGroupConnDTO(Integer groupId, String groupName, List<MemberDTO> groupMembers){
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupMembers = groupMembers;
    }

    public MemberGroupConnDTO convert(Group group){

        MemberDTO memberDTO= new MemberDTO();
        return new MemberGroupConnDTO(
                group.getId(),
                group.getName(),
                memberDTO.convertList(group.getMembers().stream().toList()));
    }

    public List<MemberGroupConnDTO> convertList(List<Group> groups){

        List<MemberGroupConnDTO> dtoList = new ArrayList<>();

        for (Group g : groups){
            dtoList.add(convert(g));
        }

        return dtoList;

    }

}
