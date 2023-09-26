package com.splitwizard.splitwizard.service;

import com.splitwizard.splitwizard.DAO.MemberRepository;
import com.splitwizard.splitwizard.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.midi.MetaMessage;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    private final MemberRepository dao;
    @Autowired
    public MemberService(MemberRepository dao){

        this.dao = dao;

    }

    public Member getById(Integer id){

        return dao.getReferenceById(id);

    }

    public List<Member> getAll(){

        return dao.findAll();
    }

    public String login(String account, String password){

        // 把所有帳號密碼拉出來
        List<Member> allMember = dao.findAll();
        List<String> allAccount = new ArrayList<>();
        List<String> allPassword = new ArrayList<>();


        for (Member m : allMember){

            allAccount.add(m.getAccount());
            allPassword.add(m.getPassword());

        }

        // 檢查帳號
        if (!allAccount.contains(account)) return "帳號或密碼錯誤";

        // 檢查密碼
        if (!allPassword.contains(password)) return "帳號或密碼錯誤";

        // 檢查帳號密碼是否符合
        Member member = dao.findByAccount(account);
        if (!member.getPassword().equals(password)) return "帳號或密碼錯誤";

        return "登入成功";


    }

}
