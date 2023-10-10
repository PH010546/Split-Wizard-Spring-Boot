package com.splitwizard.splitwizard.VO;

import com.splitwizard.splitwizard.POJO.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
@Getter @Setter
@NoArgsConstructor
public class ItemVO {
    private Integer id;
    private String name;
    private Integer amount;
    private String itemTime;
    private Timestamp createdTime;
    private Timestamp updateTime;

    public Item convertVOToPOJO(){
        Item pojo = new Item();

        pojo.setId(this.id);
        pojo.setName(this.name);
        pojo.setAmount(this.amount);
        pojo.setItemTime(this.itemTime);
        pojo.setCreatedTime(this.createdTime);
        pojo.setUpdateTime(this.updateTime);

        return pojo;
    }

    public ItemVO convertPOJOToVO(Item pojo){
        ItemVO vo = new ItemVO();

        vo.setId(pojo.getId());
        vo.setName(pojo.getName());
        vo.setItemTime(pojo.getItemTime());
        vo.setAmount(pojo.getAmount());
        vo.setCreatedTime(pojo.getCreatedTime());
        vo.setUpdateTime(pojo.getUpdateTime());

        return vo;
    }
}
