package com.splitwizard.splitwizard.service;

import com.splitwizard.splitwizard.DAO.MemberRepository;
import com.splitwizard.splitwizard.DTO.MemberDTO;
import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.model.Member;
import com.splitwizard.splitwizard.service.intf.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository dao;
    private final BCryptPasswordEncoder passwordEncoder;
    private final Result R;
    private final MemberDTO dto;
    private final HttpSession session;

    @Autowired
    public MemberServiceImpl(MemberRepository dao, HttpSession session){

        this.dao = dao;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.R = new Result();
        this.dto = new MemberDTO();
        this.session = session;
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

        try{
            // 檢查帳號是否存在
            if (dao.findByAccount(account) == null) return R.fail("帳號或密碼錯誤");


            // 檢查帳號密碼是否符合
            Member member = dao.findByAccount(account);
            if (!passwordEncoder.matches(password, member.getPassword())) return R.fail("帳號或密碼錯誤");

            return R.success(dto.convert(member));
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }

    @Override
    public Result register(Member member) {

        try{
            // 檢查帳號是否存在
            if (dao.findByAccount(member.getAccount()) != null) return R.fail("帳號已存在");

            // 加密密碼
            member.setPassword(passwordEncoder.encode(member.getPassword()));

            // 將時間加入
            member.setCreated_time(new Timestamp(System.currentTimeMillis()));
            member.setUpdate_time(new Timestamp(System.currentTimeMillis()));

            // 儲存進DB
            return R.success(dto.convert(dao.save(member)));
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }

    }

    public Result getAllMemberWithoutPassword(){

        try{
            List<Member> members = dao.findAll();
            List<Member> result = new ArrayList<>();

            for (Member m : members){
                if (!Objects.equals(m.getId(), session.getAttribute("currentUser"))){
                    result.add(m);
                }
            }

            return R.success(dto.convertList(result));
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }

}
