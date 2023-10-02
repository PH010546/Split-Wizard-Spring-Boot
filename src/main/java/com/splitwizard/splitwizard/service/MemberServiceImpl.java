package com.splitwizard.splitwizard.service;

import com.splitwizard.splitwizard.DAO.MemberRepository;
import com.splitwizard.splitwizard.Util.MemberDTO;
import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository dao;
    private final BCryptPasswordEncoder passwordEncoder;
    private final Result R;
    private final MemberDTO dto;

    @Autowired
    public MemberServiceImpl(MemberRepository dao){

        this.dao = dao;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.R = new Result();
        this.dto = new MemberDTO();

    }

    public Result getById(Integer id){

        return null;

    }

    public Result getAll(){

        try{
            return R.success(dao.findAll());
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }

    public Result login(String account, String password){

        // 檢查帳號是否存在
        if (dao.findByAccount(account) == null) return R.fail("帳號或密碼錯誤");


        // 檢查帳號密碼是否符合
        Member member = dao.findByAccount(account);
        if (!passwordEncoder.matches(password, member.getPassword())) return R.fail("帳號或密碼錯誤");

        return R.success(dto.convert(member));
    }

    @Override
    public Result register(Member member) {

        // 檢查帳號是否存在
        if (dao.findByAccount(member.getAccount()) != null) return R.fail("帳號已存在");

        // 加密密碼
        member.setPassword(passwordEncoder.encode(member.getPassword()));

        // 將時間加入
        member.setCreated_time(new Timestamp(System.currentTimeMillis()));
        member.setUpdate_time(new Timestamp(System.currentTimeMillis()));

        // 儲存進DB
        return R.success(dto.convert(dao.save(member)));
    }

    public Result getAllMemberWithoutPassword(){

        try{
            return R.success(dto.convertList(dao.findAll()));
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }

}
