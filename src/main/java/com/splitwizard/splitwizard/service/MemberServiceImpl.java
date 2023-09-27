package com.splitwizard.splitwizard.service;

import com.splitwizard.splitwizard.DAO.MemberRepository;
import com.splitwizard.splitwizard.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository dao;
    private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public MemberServiceImpl(MemberRepository dao){

        this.dao = dao;
        this.passwordEncoder = new BCryptPasswordEncoder();

    }

    public Member getById(Integer id){

        return dao.getReferenceById(id);

    }

    public List<Member> getAll(){

        return dao.findAll();
    }

    public String login(String account, String password){

        // 檢查帳號是否存在
        if (dao.findByAccount(account) == null) return "帳號或密碼錯誤";


        // 檢查帳號密碼是否符合
        Member member = dao.findByAccount(account);
        if (!passwordEncoder.matches(password, member.getPassword())) return "帳號或密碼錯誤";

        return "登入成功";
    }

    @Override
    public String register(Member member) {

        // 檢查帳號是否存在
        if (dao.findByAccount(member.getAccount()) != null) return "帳號已存在";

        // 加密密碼
        member.setPassword(passwordEncoder.encode(member.getPassword()));

        // 儲存進DB
        dao.save(member);

        return "註冊成功";
    }

}
