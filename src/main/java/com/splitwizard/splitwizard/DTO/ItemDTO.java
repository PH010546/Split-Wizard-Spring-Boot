package com.splitwizard.splitwizard.DTO;

import com.splitwizard.splitwizard.POJO.Item;
import com.splitwizard.splitwizard.VO.ItemVO;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Getter @Setter
public class ItemDTO {

    private Integer id;
    private String name;
    private Double amount;
    private String itemTime;
    private Timestamp createdTime;
    private Timestamp updateTime;

    public ItemDTO convertPOJOToDTO(Item pojo){
        ItemDTO dto = new ItemDTO();

        dto.setId(pojo.getId());
        dto.setName(pojo.getName());
        dto.setAmount(pojo.getAmount());
        dto.setItemTime(pojo.getItemTime());
        dto.setCreatedTime(pojo.getCreatedTime());
        dto.setUpdateTime(pojo.getUpdateTime());

        return dto;
    }

    public ItemDTO convertVOToDTO(ItemVO vo){
        ItemDTO dto = new ItemDTO();

        dto.setId(null);
        dto.setName(vo.getItemName());
        dto.setAmount(vo.getItemAmount());
        dto.setItemTime(vo.getItemTime());
        dto.setCreatedTime(vo.getCreatedTime());
        dto.setUpdateTime(vo.getUpdateTime());

        return dto;
    }

    public Item convertDTOToPOJO(){
        Item pojo = new Item();

        pojo.setName(this.name);
        pojo.setAmount(this.amount);
        pojo.setItemTime(this.itemTime);
        pojo.setCreatedTime(this.createdTime);
        pojo.setUpdateTime(this.updateTime);

        return pojo;
    }

    public ItemVO convertDTOToVO(){
        ItemVO vo = new ItemVO();

        vo.setItemName(this.name);
        vo.setItemAmount(this.amount);
        vo.setItemTime(this.itemTime);
        vo.setCreatedTime(this.createdTime);
        vo.setUpdateTime(this.updateTime);

        return vo;
    }
}
