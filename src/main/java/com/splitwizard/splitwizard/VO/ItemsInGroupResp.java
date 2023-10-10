package com.splitwizard.splitwizard.VO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter @Setter
public class ItemsInGroupResp {

    private Integer id;
    private String name;
    private List<ItemVO> items;

}
