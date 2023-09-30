package com.splitwizard.splitwizard.Util;

import com.splitwizard.splitwizard.model.Group;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GroupDTO {

    private Integer id;
    private String name;

    public GroupDTO convert(Group group){

        GroupDTO dto = new GroupDTO();
        dto.setId(group.getId());
        dto.setName(group.getName());

        return dto;
    }
}
