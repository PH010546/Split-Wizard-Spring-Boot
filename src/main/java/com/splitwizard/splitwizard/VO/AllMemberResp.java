package com.splitwizard.splitwizard.VO;

import com.splitwizard.splitwizard.POJO.Member;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class AllMemberResp {

    private Integer id;
    private String UID;
    private String name;

    public AllMemberResp convertPOJOToResp(Member pojo){

        AllMemberResp resp = new AllMemberResp();

        resp.setId(pojo.getId());
        resp.setName(pojo.getName());
        resp.setUID(pojo.getUID());

        return resp;

    }

    public List<AllMemberResp> convertPOJOListToRespList(List<Member> pojoList){

        List<AllMemberResp> respList = new ArrayList<>();

        for (Member pojo : pojoList){
            respList.add(convertPOJOToResp(pojo));
        }

        return respList;
    }
}
