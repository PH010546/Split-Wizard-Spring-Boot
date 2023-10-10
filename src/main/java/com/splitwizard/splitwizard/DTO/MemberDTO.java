package com.splitwizard.splitwizard.DTO;

import com.splitwizard.splitwizard.DAO.MemberRepository;
import com.splitwizard.splitwizard.POJO.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Component
public class MemberDTO {

    private Integer id;
    private String account;
    private String name;

    public MemberDTO convert(Member member){

        MemberDTO dto = new MemberDTO();

        dto.setId(member.getId());
        dto.setName(member.getName());
        dto.setAccount(member.getAccount());

        return dto;
    }

    public List<MemberDTO> convertList(List<Member> members){

        List<MemberDTO> dtoList = new ArrayList<>();

        for (Member m : members){
            dtoList.add(convert(m));
        }

        return dtoList;

    }

    public Member convertDTOToPOJO(MemberRepository dao){
        return dao.getReferenceById(this.getId());
    }

}
