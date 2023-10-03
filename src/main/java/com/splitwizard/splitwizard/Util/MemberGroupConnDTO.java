package com.splitwizard.splitwizard.Util;

import com.splitwizard.splitwizard.model.Group;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class MemberGroupConnDTO {

    private Integer group_id;
    private String group_name;
    private List<MemberDTO> members;

    // TODO: maybe can add constructor so in the method we can just call it.

    public MemberGroupConnDTO convert(Group group){

        MemberGroupConnDTO dto = new MemberGroupConnDTO();
        MemberDTO memberDTO= new MemberDTO();

        dto.setGroup_id(group.getId());
        dto.setGroup_name(group.getName());
        dto.setMembers(memberDTO.convertList(group.getMembers().stream().toList()));

        return dto;
    }

    public List<MemberGroupConnDTO> convertList(List<Group> groups){

        List<MemberGroupConnDTO> dtoList = new ArrayList<>();

        for (Group g : groups){
            dtoList.add(convert(g));
        }

        return dtoList;

    }

}
