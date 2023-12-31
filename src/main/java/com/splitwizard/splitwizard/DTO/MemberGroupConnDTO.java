package com.splitwizard.splitwizard.DTO;

import com.splitwizard.splitwizard.POJO.MemberGroupConn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class MemberGroupConnDTO implements Comparable<MemberGroupConnDTO>{

    private Integer id;
    private Integer groupId;
    private Integer memberId;
    private BigDecimal net;
    private Timestamp updateTime;

    public MemberGroupConnDTO convertPOJOToDTO(MemberGroupConn conn){
        MemberGroupConnDTO dto = new MemberGroupConnDTO();

        dto.setId(conn.getId());
        dto.setGroupId(conn.getGroupId());
        dto.setMemberId(conn.getMemberId());
        dto.setNet(conn.getNet());
        dto.setUpdateTime(conn.getUpdateTime());

        return dto;
    }

    public List<MemberGroupConnDTO> convertPOJOListToDTOList(List<MemberGroupConn> connList){

        List<MemberGroupConnDTO> dtoList = new ArrayList<>();

        for (MemberGroupConn pojo : connList){
            dtoList.add(convertPOJOToDTO(pojo));
        }

        return dtoList;
    }

    @Override
    public int compareTo(@NotNull MemberGroupConnDTO other) {
        return (this.getNet().subtract(other.getNet())).intValue();
    }
}
