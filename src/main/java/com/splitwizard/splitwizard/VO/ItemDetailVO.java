package com.splitwizard.splitwizard.VO;

import com.splitwizard.splitwizard.POJO.ItemDetail;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class ItemDetailVO {

    private Integer id; // member_id
    private String name; // member_name
    private Long amount;
    private Boolean payer;

    public ItemDetail convertVOToPOJO(){

        ItemDetail itemDetail = new ItemDetail();

        itemDetail.setMemberId(this.id);
        itemDetail.setAmount(this.amount);
        itemDetail.setPayer(this.payer);


        return itemDetail;
    }
}
