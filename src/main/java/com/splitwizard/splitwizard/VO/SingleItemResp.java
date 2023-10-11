package com.splitwizard.splitwizard.VO;

import com.splitwizard.splitwizard.DAO.MemberRepository;
import com.splitwizard.splitwizard.POJO.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter @Setter
public class SingleItemResp {

    private String name;
    private String time;
    private Integer amount;
    private List<ItemDetailVO> details;



    public SingleItemResp convertItemToResp(Item item, MemberRepository memberDAO){

        ItemDetailVO itemDetailVO = new ItemDetailVO();

        SingleItemResp resp = new SingleItemResp();

        resp.setName(item.getName());
        resp.setAmount(item.getAmount());
        resp.setTime(item.getItemTime());
        resp.setDetails(itemDetailVO.convertPOJOListToVOList(item.getItemDetails(), memberDAO));

        return resp;

    }

}
