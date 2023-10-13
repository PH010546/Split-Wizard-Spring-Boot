package com.splitwizard.splitwizard.VO;

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
    private Long amount;
    private Boolean status;

    public ResultResp convertPOJOToResp(Results pojo){
        ResultResp resp = new ResultResp();
        MemberDTO memberDTO = new MemberDTO();

        resp.setId(pojo.getId());
        resp.setStatus(pojo.getStatus());
        resp.setAmount(pojo.getAmount());
        resp.setOwerId(pojo.getOwerId());
        resp.setPayerId(pojo.getPayerId());
        resp.setOwer(memberDTO.convert(pojo.getOwer()));
        resp.setPayer(memberDTO.convert(pojo.getPayer()));

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
