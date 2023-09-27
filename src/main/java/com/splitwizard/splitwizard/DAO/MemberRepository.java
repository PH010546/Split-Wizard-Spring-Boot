package com.splitwizard.splitwizard.DAO;

import com.splitwizard.splitwizard.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    Member findByAccount(String account);

}