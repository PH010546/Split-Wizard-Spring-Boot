package com.splitwizard.splitwizard.VO.resp;

import com.splitwizard.splitwizard.VO.OverViewVO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class OverViewResp {

    private String groupName;
    private Boolean groupArchive;
    private List<OverViewVO> overViews;

}
