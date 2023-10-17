package com.splitwizard.splitwizard.DTO;

import com.splitwizard.splitwizard.POJO.Results;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ResultDTO {
    private Integer id;
    private Integer groupId;
    private Integer owerId;
    private Integer payerId;
    private Float amount;
    private Boolean status;
    private Timestamp createdTime;
    private Timestamp updateTime;

    public List<Results> convertDTOListToPOJOList(List<ResultDTO> dtoList){

        List<Results> resultList = new ArrayList<>();

        for (ResultDTO dto : dtoList){
            resultList.add(dto.convertDTOToPOJO());
        }

        return resultList;
    }

    public Results convertDTOToPOJO(){

        Results pojo = new Results();

        pojo.setAmount(this.amount);
        pojo.setStatus(this.status);
        pojo.setGroupId(this.groupId);
        pojo.setOwerId(this.owerId);
        pojo.setPayerId(this.payerId);
        pojo.setCreatedTime(this.createdTime);
        pojo.setUpdateTime(this.updateTime);
        pojo.setId(this.id);

        return pojo;
    }

}
