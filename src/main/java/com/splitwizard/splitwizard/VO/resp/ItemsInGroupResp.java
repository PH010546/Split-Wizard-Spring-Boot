package com.splitwizard.splitwizard.VO.resp;

import com.splitwizard.splitwizard.DAO.MemberRepository;
import com.splitwizard.splitwizard.DTO.MemberDTO;
import com.splitwizard.splitwizard.POJO.Item;
import com.splitwizard.splitwizard.POJO.ItemDetail;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
@Getter @Setter
@NoArgsConstructor
public class ItemsInGroupResp {

    private Integer id;
    private String name;
    private Boolean redirect;
    private Boolean archive;
    private List<ItemsInGroupRespItem> items;

    public ItemsInGroupResp(Integer id, String name, Boolean redirect, Boolean archive,
                            List<Item> itemsPOJO,
                            MemberRepository memberDAO){

        this.id = id;
        this.name = name;
        this.redirect = redirect;
        this.archive = archive;
        this.items = convertItemListToRespList(itemsPOJO, memberDAO);
    }



    private List<ItemsInGroupRespItem> convertItemListToRespList(List<Item> items, MemberRepository memberDAO){

        List<ItemsInGroupRespItem> respItems = new ArrayList<>();
        ItemsInGroupRespItem respItem = new ItemsInGroupRespItem();

        for (Item item : items){
            respItem = respItem.convertPOJOToResp(item);
            respItem.setDetails(item.getItemDetails(), memberDAO);
            respItems.add(respItem);
        }

        return respItems;

    }
    @Getter @Setter
    @NoArgsConstructor
    private class ItemsInGroupRespItem{

        private Integer id;
        private String itemName;
        private Double itemAmount;
        private String itemTime;
        private Timestamp createdTime;
        private Timestamp updateTime;
        private List<ItemsInGroupRespItemDetail> details;

        private void setDetails(List<ItemDetail> detailsPOJO, MemberRepository memberDAO){
            this.details = convertDetailPOJOListToRespList(detailsPOJO, memberDAO);
        }

        private List<ItemsInGroupRespItemDetail> convertDetailPOJOListToRespList(List<ItemDetail> detailsPOJO,
                                                                                 MemberRepository memberDAO){

            List<ItemsInGroupRespItemDetail> respItemDetails = new ArrayList<>();
            ItemsInGroupRespItemDetail respItemDetail = new ItemsInGroupRespItemDetail();

            for (ItemDetail detail : detailsPOJO){
                respItemDetail = respItemDetail.convertDetailPOJOToResp(detail, memberDAO);
                if (respItemDetail.getPayer()) respItemDetails.add(respItemDetail);
            }

            return respItemDetails;
        }

        public ItemsInGroupRespItem convertPOJOToResp(Item pojo){
            ItemsInGroupRespItem resp = new ItemsInGroupRespItem();

            resp.setId(pojo.getId());
            resp.setItemName(pojo.getName());
            resp.setItemTime(pojo.getItemTime());
            resp.setItemAmount(pojo.getAmount());
            resp.setCreatedTime(pojo.getCreatedTime());
            resp.setUpdateTime(pojo.getUpdateTime());

            return resp;
        }
    }

    @Getter @Setter
    @NoArgsConstructor
    private class ItemsInGroupRespItemDetail{

        private Integer id;
        private Boolean payer;
        private Double amount;
        private MemberDTO member;

        private ItemsInGroupRespItemDetail convertDetailPOJOToResp(ItemDetail detail, MemberRepository memberDAO){

            ItemsInGroupRespItemDetail resp = new ItemsInGroupRespItemDetail();
            MemberDTO dto = new MemberDTO();

            resp.setId(detail.getId());
            resp.setPayer(detail.getPayer());
            resp.setAmount(detail.getAmount());
            resp.setMember(dto.convert(memberDAO.getReferenceById(detail.getMemberId())));

            return resp;
        }

    }

}
