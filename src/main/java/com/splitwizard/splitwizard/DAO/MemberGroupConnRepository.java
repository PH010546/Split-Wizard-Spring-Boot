package com.splitwizard.splitwizard.DAO;

import com.splitwizard.splitwizard.POJO.MemberGroupConn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberGroupConnRepository extends JpaRepository<MemberGroupConn, Integer> {
}
