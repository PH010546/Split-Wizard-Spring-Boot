package com.splitwizard.splitwizard.VO.resp;

import com.splitwizard.splitwizard.VO.MemberVO;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginResp {

    MemberVO member;
    String authToken;

}
