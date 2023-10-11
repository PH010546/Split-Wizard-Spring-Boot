package com.splitwizard.splitwizard.service;

import com.splitwizard.splitwizard.DAO.GroupRepository;
import com.splitwizard.splitwizard.DAO.ItemDAO;
import com.splitwizard.splitwizard.DAO.MemberRepository;
import com.splitwizard.splitwizard.POJO.Group;
import com.splitwizard.splitwizard.POJO.Item;
import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.VO.ItemVO;
import com.splitwizard.splitwizard.VO.ItemsInGroupResp;
import com.splitwizard.splitwizard.VO.SingleItemResp;
import com.splitwizard.splitwizard.service.intf.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemDAO itemDAO;
    private final GroupRepository groupDAO;
    private final Result R;
    private final MemberRepository memberDAO;
    @Autowired
    public ItemServiceImpl(ItemDAO itemDAO, GroupRepository groupDAO, MemberRepository memberDAO){
        this.itemDAO = itemDAO;
        this.groupDAO = groupDAO;
        this.memberDAO = memberDAO;
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
    public Result getSingleItem(Integer itemId) {

        try {

            SingleItemResp resp = new SingleItemResp();

            return R.success(resp.convertItemToResp(itemDAO.getReferenceById(itemId), memberDAO));
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
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
