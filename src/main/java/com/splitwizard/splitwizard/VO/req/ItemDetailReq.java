package com.splitwizard.splitwizard.VO.req;

import com.splitwizard.splitwizard.VO.ItemDetailVO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter @Setter
public class ItemDetailReq {

    private List<ItemDetailVO> payer;
    private List<ItemDetailVO> ower;

}
