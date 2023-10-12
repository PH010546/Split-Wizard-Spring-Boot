package com.splitwizard.splitwizard.DAO;

import com.splitwizard.splitwizard.DTO.ResultDTO;
import com.splitwizard.splitwizard.POJO.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultDAO extends JpaRepository<Result, Integer> {

    List<ResultDTO> findResultByGroupId(Integer groupId);
}
