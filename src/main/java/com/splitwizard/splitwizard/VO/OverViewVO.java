package com.splitwizard.splitwizard.VO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class OverViewVO {

    private Integer memberId;
    private String memberName;
    private BigDecimal memberNet;

}
