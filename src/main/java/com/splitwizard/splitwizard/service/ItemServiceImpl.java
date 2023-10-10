package com.splitwizard.splitwizard.service;

import com.splitwizard.splitwizard.DAO.GroupRepository;
import com.splitwizard.splitwizard.DAO.ItemDAO;
import com.splitwizard.splitwizard.POJO.Group;
import com.splitwizard.splitwizard.POJO.Item;
import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.VO.ItemVO;
import com.splitwizard.splitwizard.VO.ItemsInGroupResp;
import com.splitwizard.splitwizard.service.intf.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemDAO itemDAO;
    private final GroupRepository groupDAO;
    private final Result R;
    @Autowired
    public ItemServiceImpl(ItemDAO itemDAO, GroupRepository groupDAO){
        this.itemDAO = itemDAO;
        this.groupDAO = groupDAO;
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

            ItemsInGroupResp resp = new ItemsInGroupResp();
            ItemVO itemVO = new ItemVO();
            List<ItemVO> voList = new ArrayList<>();

            List<Item> items = group.getItems();

            for (Item i : items){
                voList.add(itemVO.convertPOJOToVO(i));
            }

            resp.setId(group.getId());
            resp.setName(group.getName());
            resp.setItems(voList);

            return R.success(resp);

        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }

    @Override
    public Result getSingleItem(Integer itemId) {
        return null;
    }

    @Override
    public Result editItem(ItemVO itemVO) {
        return null;
    }

    @Override
    public Result deleteItem(Integer itemId) {
        return null;
    }
}