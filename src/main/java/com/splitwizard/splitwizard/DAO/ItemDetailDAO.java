package com.splitwizard.splitwizard.DAO;

import com.splitwizard.splitwizard.POJO.ItemDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemDetailDAO extends JpaRepository<ItemDetail, Integer> {

    List<ItemDetail> findAllByItemId(Integer itemId);
    void deleteItemDetailsByItemId(Integer itemId);

    List<ItemDetail> findAllByMemberIdAndItemId(Integer memberId, Integer itemId);

}
