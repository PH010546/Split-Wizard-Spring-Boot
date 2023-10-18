package com.splitwizard.splitwizard.service;

import com.splitwizard.splitwizard.DAO.*;
import com.splitwizard.splitwizard.POJO.Group;
import com.splitwizard.splitwizard.POJO.Item;
import com.splitwizard.splitwizard.POJO.ItemDetail;
import com.splitwizard.splitwizard.POJO.MemberGroupConn;
import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.VO.ItemVO;
import com.splitwizard.splitwizard.VO.resp.ItemsInGroupResp;
import com.splitwizard.splitwizard.VO.resp.SingleItemResp;
import com.splitwizard.splitwizard.service.intf.ItemService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemDAO itemDAO;
    private final GroupRepository groupDAO;
    private final Result R;
    private final MemberRepository memberDAO;
    private final ItemDetailDAO itemDetailDAO;
    private final MemberGroupConnRepository memberGroupDAO;
    @Autowired
    public ItemServiceImpl(ItemDAO itemDAO, GroupRepository groupDAO, MemberRepository memberDAO,
                           ItemDetailDAO itemDetailDAO, MemberGroupConnRepository memberGroupDAO) {
        this.itemDAO = itemDAO;
        this.groupDAO = groupDAO;
        this.memberDAO = memberDAO;
        this.itemDetailDAO = itemDetailDAO;
        this.memberGroupDAO = memberGroupDAO;
        this.R = new Result();
    }

    @Override
    public Result addItem(ItemVO itemVO, Integer groupId) {
        try{
            Item item = itemVO.convertVOToPOJO();
            item.setGroupId(groupId);

            return R.success(itemDAO.save(item));
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }

    @Override
    public Result getItemsInGroup(Integer groupId) {

        try{

            // TODO: need to modify. (ItemVO.convertList())
            Group group = groupDAO.getReferenceById(groupId);

            return R.success(new ItemsInGroupResp(
                    group.getId(),
                    group.getName(),
                    group.getRedirect(),
                    group.getArchive(),
                    group.getItems(),
                    memberDAO
                    ));

        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }

    @Override
    public Result getSingleItem(Integer groupId, Integer itemId) {

        try {

            SingleItemResp resp = new SingleItemResp();

            return R.success(resp.convertItemToResp(itemDAO.getReferenceById(itemId),groupId , memberDAO, groupDAO));
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }

    @Override
    public Result editItem(ItemVO itemVO, Integer itemId) {

        try{
            Item newItem = itemDAO.getReferenceById(itemId);

            newItem.setName(itemVO.getItemName());
            newItem.setItemTime(itemVO.getItemTime());
            newItem.setAmount(itemVO.getItemAmount());
            newItem.setUpdateTime(new Timestamp(System.currentTimeMillis()));

            itemDAO.save(newItem);

            return R.success(null);
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Result deleteItem(Integer groupId ,Integer itemId) {

        try{

            // find all itemDetails
            List<ItemDetail> details = itemDetailDAO.findAllByItemId(itemId);

            // update net in MemberGroupConn
            for (ItemDetail detail : details){
                MemberGroupConn conn = memberGroupDAO.findByGroupIdAndMemberId(groupId, detail.getMemberId());

                // if payer = true, then minus the amount
                if (detail.getPayer()) {
                    conn.setNet(conn.getNet().subtract(detail.getAmount()));

                    // if payer = false, then plus the amount
                }else{
                    conn.setNet(conn.getNet().add(detail.getAmount()));
                }
                // update time
                conn.setUpdateTime(new Timestamp(System.currentTimeMillis()));

                // save the change in MemberGroupConn
                memberGroupDAO.save(conn);
            }

            itemDAO.deleteById(itemId);
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
