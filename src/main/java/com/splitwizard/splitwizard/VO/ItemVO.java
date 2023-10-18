package com.splitwizard.splitwizard.VO;

import com.splitwizard.splitwizard.POJO.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter @Setter
@NoArgsConstructor
public class ItemVO {
    private Integer id;
    private String itemName;
    private BigDecimal itemAmount;
    private String itemTime;
    private Timestamp createdTime;
    private Timestamp updateTime;

    public Item convertVOToPOJO(){
        Item pojo = new Item();

        pojo.setId(this.id);
        pojo.setName(this.itemName);
        pojo.setAmount(this.itemAmount);
        pojo.setItemTime(this.itemTime);
        pojo.setCreatedTime(this.createdTime);
        pojo.setUpdateTime(this.updateTime);

        return pojo;
    }

    public ItemVO convertPOJOToVO(Item pojo){
        ItemVO vo = new ItemVO();

        vo.setId(pojo.getId());
        vo.setItemName(pojo.getName());
        vo.setItemTime(pojo.getItemTime());
        vo.setItemAmount(pojo.getAmount());
        vo.setCreatedTime(pojo.getCreatedTime());
        vo.setUpdateTime(pojo.getUpdateTime());

        return vo;
    }
}
