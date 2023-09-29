package com.splitwizard.splitwizard.Util;

import com.splitwizard.splitwizard.model.Member;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
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

}
