package com.splitwizard.splitwizard.DTO;

import com.splitwizard.splitwizard.DAO.GroupRepository;
import com.splitwizard.splitwizard.POJO.Group;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter @Setter
@Component
public class GroupDTO {

    private Integer id;
    private String name;

    public GroupDTO convert(Group group){

        GroupDTO dto = new GroupDTO();
        dto.setId(group.getId());
        dto.setName(group.getName());

        return dto;
    }

    public Group convertDTOToPOJO(GroupRepository dao){
        return dao.getReferenceById(this.getId());
    }
}
