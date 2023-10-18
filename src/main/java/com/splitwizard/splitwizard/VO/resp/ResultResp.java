package com.splitwizard.splitwizard.VO.resp;

import com.splitwizard.splitwizard.DTO.MemberDTO;
import com.splitwizard.splitwizard.POJO.Results;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ResultResp {

    private Integer id;
    private Integer owerId;
    private MemberDTO ower;
    private Integer payerId;
    private MemberDTO payer;
    private Double amount;
    private Boolean status;

    public ResultResp convertPOJOToResp(Results pojo){
        ResultResp resp = new ResultResp();
        MemberDTO memberDTO = new MemberDTO();

        resp.setId(pojo.getId());
        resp.setStatus(pojo.getStatus());
        resp.setAmount(pojo.getAmount());
        resp.setOwerId(pojo.getGiverId());
        resp.setPayerId(pojo.getTakerId());
        resp.setOwer(memberDTO.convert(pojo.getGiver()));
        resp.setPayer(memberDTO.convert(pojo.getTaker()));

        return resp;
    }

    public List<ResultResp> convertPOJOListToRespList(List<Results> pojoList){

        List<ResultResp> respList = new ArrayList<>();

        for (Results pojo : pojoList){
            respList.add(convertPOJOToResp(pojo));
        }

        return respList;
    }
}
