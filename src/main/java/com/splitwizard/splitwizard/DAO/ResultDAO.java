package com.splitwizard.splitwizard.DAO;

import com.splitwizard.splitwizard.POJO.Results;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultDAO extends JpaRepository<Results, Integer> {

    List<Results> findResultByGroupId(Integer groupId);
}
