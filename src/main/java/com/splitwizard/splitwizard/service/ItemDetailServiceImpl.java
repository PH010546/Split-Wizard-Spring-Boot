package com.splitwizard.splitwizard.service;

import com.splitwizard.splitwizard.DAO.ItemDetailDAO;
import com.splitwizard.splitwizard.DAO.MemberGroupConnRepository;
import com.splitwizard.splitwizard.POJO.ItemDetail;
import com.splitwizard.splitwizard.POJO.MemberGroupConn;
import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.VO.ItemDetailVO;
import com.splitwizard.splitwizard.VO.req.ItemDetailReq;
import com.splitwizard.splitwizard.service.intf.ItemDetailService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ItemDetailServiceImpl implements ItemDetailService {
    private final ItemDetailDAO itemDetailDAO;
    private final MemberGroupConnRepository memberGroupConnDAO;
    private final Result R;
    @Autowired
    public ItemDetailServiceImpl(ItemDetailDAO itemDetailDAO, MemberGroupConnRepository memberGroupConnDAO){
        this.itemDetailDAO = itemDetailDAO;
        this.memberGroupConnDAO = memberGroupConnDAO;
        this.R = new Result();
    }
    @Override
    @Transactional
    public Result addItemDetails(Integer groupId, Integer itemId, ItemDetailReq req) {

        try{
            List<ItemDetailVO> payers = req.getPayer();
            List<ItemDetailVO> owers = req.getOwer();
            ItemDetail detail;
            List<ItemDetail> details = new ArrayList<>();

            // pull all the data of this group from member_group_conn
            List<MemberGroupConn> connList = memberGroupConnDAO.findAllByGroupId(groupId);


            // update once for all
            List<MemberGroupConn> connresults = new ArrayList<>();

            for (ItemDetailVO vo : payers){

                detail = vo.convertVOToPOJO();
                detail.setItemId(itemId);
                detail.setUpdateTime(new Timestamp(System.currentTimeMillis()));

                details.add(detail);

                // edit net in member_group_conn
                for (MemberGroupConn c : connList){
                    if (Objects.equals(c.getMemberId(), vo.getId())){
                        // for payers, the amount is positive.
                        c.setNet(c.getNet().add(vo.getAmount())); // c.getNet() + vo.getAmount
                        c.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                        connresults.add(c);
                    }
                }

            }

            for (ItemDetailVO vo : owers){
                detail = vo.convertVOToPOJO();
                detail.setItemId(itemId);
                detail.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                details.add(detail);

                // edit net in member_group_conn
                for (MemberGroupConn c : connList){
                    if (Objects.equals(c.getMemberId(), vo.getId())){
                        // for owers, the amount is negative.
                        c.setNet(c.getNet().subtract(vo.getAmount()));
                        c.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                        connresults.add(c);
                    }
                }
            }

            itemDetailDAO.saveAll(details);
            memberGroupConnDAO.saveAll(connresults);

            return R.success(null);
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Result editItemDetails(Integer groupId , Integer itemId, ItemDetailReq req) {

        try{
            itemDetailDAO.deleteItemDetailsByItemId(itemId);
            addItemDetails(groupId, itemId, req);
            return R.success(null);
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }

//    private BigDecimal round(BigDecimal num){
//        return Math.round(num*100.0)/100.0;
//    }
}
