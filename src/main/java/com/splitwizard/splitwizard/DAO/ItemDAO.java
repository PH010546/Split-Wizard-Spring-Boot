package com.splitwizard.splitwizard.DAO;

import com.splitwizard.splitwizard.POJO.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDAO extends JpaRepository<Item, Integer> {
}
