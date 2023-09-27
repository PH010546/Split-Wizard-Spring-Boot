package com.splitwizard.splitwizard.service;

import com.splitwizard.splitwizard.DAO.MemberRepository;
import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository dao;
    private final BCryptPasswordEncoder passwordEncoder;
    private final Result R;

    @Autowired
    public MemberServiceImpl(MemberRepository dao){

        this.dao = dao;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.R = new Result();

    }

    public Member getById(Integer id){

        return dao.getReferenceById(id);

    }

    public List<Member> getAll(){

        return dao.findAll();
    }

    public Result login(String account, String password){

        // 檢查帳號是否存在
        if (dao.findByAccount(account) == null) return R.fail("帳號或密碼錯誤");


        // 檢查帳號密碼是否符合
        Member member = dao.findByAccount(account);
        if (!passwordEncoder.matches(password, member.getPassword())) R.fail("帳號或密碼錯誤");

        return R.success(member.getId());
    }

    @Override
    public Result register(Member member) {

        // 檢查帳號是否存在
        if (dao.findByAccount(member.getAccount()) != null) return R.fail("帳號已存在");

        // 加密密碼
        member.setPassword(passwordEncoder.encode(member.getPassword()));

        // 儲存進DB
        dao.save(member);

        return R.success(member.getId());
    }

}
