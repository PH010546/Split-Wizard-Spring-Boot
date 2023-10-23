package com.splitwizard.splitwizard.VO.resp;

import com.splitwizard.splitwizard.DTO.ItemDTO;
import com.splitwizard.splitwizard.POJO.ItemDetail;
import com.splitwizard.splitwizard.POJO.Results;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
@Getter @Setter
public class OverViewDetailsResp {

    List<ItemDetailInOverViewResp> details;
    List<ResultInOverViewResp> results;

    public void convertAndSetDetails(List<ItemDetail> detailList){
        setDetails(new ItemDetailInOverViewResp().convertPOJOListToRespList(detailList));
    }

    public void convertAndSetResults(List<Results> resultList){

        List<ResultInOverViewResp> resultRespList = new ArrayList<>(
                new ResultInOverViewResp().convertPOJOListToRespList(resultList).stream()
                .filter(resultInOverViewResp -> resultInOverViewResp.status).sorted()
                .toList());

        setResults(resultRespList);
    }

    @Getter @Setter
    public class ItemDetailInOverViewResp implements Comparable<ItemDetailInOverViewResp>{
        //details 幫我依照itemTimeASC，

        Integer id;
        Integer memberId;
        BigDecimal amount;
        Boolean payer;
        ItemDTO item;


        public ItemDetailInOverViewResp convertPOJOToResp(ItemDetail pojo){
            ItemDetailInOverViewResp resp = new ItemDetailInOverViewResp();

            resp.setId(pojo.getId());
            resp.setMemberId(pojo.getMemberId());
            resp.setAmount(pojo.getAmount());
            resp.setPayer(pojo.getPayer());
            resp.setItem(new ItemDTO().convertPOJOToDTO(pojo.getItem()));

            return resp;
        }

        public List<ItemDetailInOverViewResp> convertPOJOListToRespList(List<ItemDetail> pojoList){
            List<ItemDetailInOverViewResp> respList = new ArrayList<>();

            for (ItemDetail pojo : pojoList){
                respList.add(convertPOJOToResp(pojo));
            }

            respList.sort(null);

            return respList;
        }

        @Override
        public int compareTo(@NotNull ItemDetailInOverViewResp other) {

            int thisTime = Long.valueOf(this.getItem().getItemTime().replaceAll("\\D", "")).intValue();
            int otherTime = Long.valueOf(other.getItem().getItemTime().replaceAll("\\D", "")).intValue();

            return thisTime - otherTime;
        }
    }

    @Getter @Setter
    public class ResultInOverViewResp implements Comparable<ResultInOverViewResp>{
        //results只要status是true的就好喔!

        Integer id;
        Integer groupId;
        Integer giverId;
        Integer takerId;
        BigDecimal amount;
        Boolean status;
        Timestamp createdTime;
        Timestamp updatedTime;

        public ResultInOverViewResp convertPOJOToResp(Results pojo){
            ResultInOverViewResp resp = new ResultInOverViewResp();

            resp.setId(pojo.getId());
            resp.setGroupId(pojo.getGroupId());
            resp.setGiverId(pojo.getGiverId());
            resp.setTakerId(pojo.getTakerId());
            resp.setAmount(pojo.getAmount());
            resp.setStatus(pojo.getStatus());
            resp.setCreatedTime(pojo.getCreatedTime());
            resp.setUpdatedTime(pojo.getUpdateTime());

            return resp;
        }

        public List<ResultInOverViewResp> convertPOJOListToRespList(List<Results> pojoList){
            List<ResultInOverViewResp> respList = new ArrayList<>();

            for (Results pojo : pojoList){
                respList.add(convertPOJOToResp(pojo));
            }
            return respList;
        }

        @Override
        public int compareTo(@NotNull ResultInOverViewResp o) {
            return this.updatedTime.compareTo(o.updatedTime);
        }
    }
}
