package com.splitwizard.splitwizard.VO.resp;

import com.splitwizard.splitwizard.DTO.MemberDTO;
import com.splitwizard.splitwizard.POJO.Results;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ResultResp {

    private Integer id;
    private Integer giverId;
    private MemberDTO giver;
    private Integer takerId;
    private MemberDTO taker;
    private BigDecimal amount;
    private Boolean status;

    public ResultResp convertPOJOToResp(Results pojo){
        ResultResp resp = new ResultResp();
        MemberDTO memberDTO = new MemberDTO();

        resp.setId(pojo.getId());
        resp.setStatus(pojo.getStatus());
        resp.setAmount(pojo.getAmount());
        resp.setGiverId(pojo.getGiverId());
        resp.setTakerId(pojo.getTakerId());
        resp.setGiver(memberDTO.convert(pojo.getGiver()));
        resp.setTaker(memberDTO.convert(pojo.getTaker()));

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
