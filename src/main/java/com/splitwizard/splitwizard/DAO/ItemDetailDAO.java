package com.splitwizard.splitwizard.DAO;

import com.splitwizard.splitwizard.POJO.ItemDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemDetailDAO extends JpaRepository<ItemDetail, Integer> {

    public List<ItemDetail> findAllByItemId(Integer itemId);
}
