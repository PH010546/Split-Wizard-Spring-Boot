package com.splitwizard.splitwizard.VO;

import com.splitwizard.splitwizard.DAO.MemberRepository;
import com.splitwizard.splitwizard.POJO.ItemDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ItemDetailVO {

    private Integer id; // member_id
    private String name; // member_name
    private Double amount;
    private Boolean payer;

    public ItemDetail convertVOToPOJO(){

        ItemDetail itemDetail = new ItemDetail();

        itemDetail.setMemberId(this.id);
        itemDetail.setAmount(this.amount);
        itemDetail.setPayer(this.payer);


        return itemDetail;
    }

    public ItemDetailVO convertPOJOToVO(ItemDetail pojo, MemberRepository memberDAO){

        ItemDetailVO vo = new ItemDetailVO();

        vo.setName(memberDAO.getReferenceById(pojo.getMemberId()).getName());
        vo.setId(pojo.getMemberId());
        vo.setAmount(pojo.getAmount());
        vo.setPayer(pojo.getPayer());

        return vo;
    }

    public List<ItemDetailVO> convertPOJOListToVOList(List<ItemDetail> pojos, MemberRepository memberDAO){

        List<ItemDetailVO> vos = new ArrayList<>();
        ItemDetailVO vo = new ItemDetailVO();

        for (ItemDetail pojo : pojos){
            vos.add(vo.convertPOJOToVO(pojo, memberDAO));
        }

        return vos;

    }
}
