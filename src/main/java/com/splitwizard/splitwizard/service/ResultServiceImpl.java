package com.splitwizard.splitwizard.service;

import com.splitwizard.splitwizard.DAO.MemberGroupConnRepository;
import com.splitwizard.splitwizard.DAO.ResultDAO;
import com.splitwizard.splitwizard.DTO.MemberGroupConnDTO;
import com.splitwizard.splitwizard.DTO.ResultDTO;
import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.service.intf.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {

    private final MemberGroupConnRepository connDAO;
    private final Result R;
    private final ResultDAO resultDAO;
    @Autowired
    public ResultServiceImpl(MemberGroupConnRepository connDAO, ResultDAO resultDAO){
        this.R = new Result();
        this.resultDAO = resultDAO;
        this.connDAO = connDAO;
    }
    @Override
    public Result getResult() {
        return null;
    }

    @Override
    public Result createSettlement(Integer groupId) {

        try{
            MemberGroupConnDTO dto = new MemberGroupConnDTO();
            // find all connection data in this group.
            List<MemberGroupConnDTO> connList = dto.convertPOJOListToDTOList(connDAO.findAllByGroupId(groupId));


            // split into two groups: payer (who should receive money) and ower (who should pay)

            List<MemberGroupConnDTO> payers = new ArrayList<>();
            List<MemberGroupConnDTO> owers = new ArrayList<>();

            for (MemberGroupConnDTO conn : connList){
                if (conn.getNet() > 0) payers.add(conn);
                else {
                    conn.setNet(conn.getNet() * (-1));
                    owers.add(conn);
                }
            }
            // make their net to absolute value and sort ASC
            payers.sort(null);
            owers.sort(null);

            int payerPointer = 0;
            int owerPointer = 0;
            long payerNet = payers.get(payerPointer).getNet();
            long owerNet = owers.get(owerPointer).getNet();

            ResultDTO resultDTO = new ResultDTO();
            List<ResultDTO> dtoList = new ArrayList<>();

            // compare the first two
            while(true){
                int payerId = payers.get(payerPointer).getMemberId();
                int owerId = owers.get(owerPointer).getMemberId();

                // if the payer's net is more than ower's, then payer's net minus ower's,
                // and use the same payer to compare the next ower.
                if (payerNet > owerNet){
                    owerPointer++;
                    if (owerPointer > owers.size()-1) break;

                    payerNet = payerNet - owerNet;
                    owerNet = owers.get(owerPointer).getNet();

                    resultDTO.setPayerId(payerId);
                    resultDTO.setOwerId(owerId);
                    resultDTO.setAmount(owerNet);
                    resultDTO.setGroupId(groupId);

                    dtoList.add(resultDTO);
                    // if the payer's net is less than ower's, then ower's net minus payer's,
                    // and use the next payer to compare the same ower.
                }else if (owerNet > payerNet){
                    payerPointer++;
                    if (payerPointer >= payers.size()-1) break;

                    owerNet = owerNet - payerNet;
                    payerNet = payers.get(payerPointer).getNet();

                    resultDTO.setPayerId(payerId);
                    resultDTO.setOwerId(owerId);
                    resultDTO.setAmount(payerNet);
                    resultDTO.setGroupId(groupId);

                    dtoList.add(resultDTO);
                }else{

                    resultDTO.setPayerId(payerId);
                    resultDTO.setOwerId(owerId);
                    resultDTO.setAmount(payerNet);
                    resultDTO.setGroupId(groupId);

                    dtoList.add(resultDTO);

                    payerPointer++;
                    owerPointer++;
                    if (payerPointer >= payers.size()-1) break;
                    if (owerPointer > owers.size()-1) break;
                }
            }

            resultDAO.saveAll(resultDTO.convertDTOListToPOJOList(dtoList));
            return R.success(null);
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }

    @Override
    public Result switchResultStatus(Integer resultId) {
        return null;
    }
}
