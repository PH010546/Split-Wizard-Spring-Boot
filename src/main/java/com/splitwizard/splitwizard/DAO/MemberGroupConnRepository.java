package com.splitwizard.splitwizard.DAO;

import com.splitwizard.splitwizard.POJO.MemberGroupConn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberGroupConnRepository extends JpaRepository<MemberGroupConn, Integer> {

    List<MemberGroupConn> findAllByGroupId(Integer groupId);
    MemberGroupConn findByGroupIdAndMemberId(Integer groupId, Integer memberId);
}
