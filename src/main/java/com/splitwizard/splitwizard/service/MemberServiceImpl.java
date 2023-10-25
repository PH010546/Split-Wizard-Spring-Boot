package com.splitwizard.splitwizard.service;

import com.splitwizard.splitwizard.DAO.MemberRepository;
import com.splitwizard.splitwizard.Jwt.JwtUtil;
import com.splitwizard.splitwizard.Jwt.UserDetailsImpl;
import com.splitwizard.splitwizard.POJO.Member;
import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.VO.MemberVO;
import com.splitwizard.splitwizard.VO.resp.AllMemberResp;
import com.splitwizard.splitwizard.VO.resp.LoginResp;
import com.splitwizard.splitwizard.service.intf.MemberService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository dao;
    private final BCryptPasswordEncoder passwordEncoder;
    private final Result R;
//    private final MemberDTO dto;

    @Autowired
    public MemberServiceImpl(MemberRepository dao){

        this.dao = dao;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.R = new Result();
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

            // 轉換成response
            LoginResp resp = new LoginResp();
            MemberVO memberVO = new MemberVO();

            memberVO.setId(member.getId());
            memberVO.setAccount(member.getAccount());
            memberVO.setName(member.getName());
            memberVO.setUID(member.getUID());

            resp.setMember(memberVO);
            resp.setAuthToken(new JwtUtil().createToken(
                    member.getAccount(),
                    List.of(member.getAuthority()),
                    member.getId()));

            return R.success(resp);
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

            // 新增UID
            member.setUID("#" + RandomStringUtils.randomAlphanumeric(5).toUpperCase());

            // 儲存進DB
            return R.success(new AllMemberResp().convertPOJOToResp(dao.save(member)));
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }

    }

    public Result getAllMemberWithoutPassword(){

        try{
            List<Member> members = dao.findAll();
            List<Member> result = new ArrayList<>();
            Integer currentUserId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();



            for (Member m : members){
                if (!Objects.equals(m.getId(), currentUserId)){
                    result.add(m);
                }
            }

            return R.success(new AllMemberResp().convertPOJOListToRespList(result));
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }

}
