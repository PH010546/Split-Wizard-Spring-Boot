package com.splitwizard.splitwizard.service;

import com.splitwizard.splitwizard.DAO.GroupRepository;
import com.splitwizard.splitwizard.DAO.MemberGroupConnRepository;
import com.splitwizard.splitwizard.DAO.ResultDAO;
import com.splitwizard.splitwizard.DTO.MemberGroupConnDTO;
import com.splitwizard.splitwizard.DTO.ResultDTO;
import com.splitwizard.splitwizard.POJO.Group;
import com.splitwizard.splitwizard.POJO.MemberGroupConn;
import com.splitwizard.splitwizard.POJO.Results;
import com.splitwizard.splitwizard.Util.Result;
import com.splitwizard.splitwizard.VO.resp.ResultResp;
import com.splitwizard.splitwizard.service.intf.ResultService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {

    private final MemberGroupConnRepository connDAO;
    private final Result R;
    private final ResultDAO resultDAO;
    private final ResultDTO DTO;
    private final GroupRepository groupDAO;
    @Autowired
    public ResultServiceImpl(MemberGroupConnRepository connDAO,
                             ResultDAO resultDAO,
                             GroupRepository groupDAO){
        this.R = new Result();
        this.resultDAO = resultDAO;
        this.connDAO = connDAO;
        this.groupDAO = groupDAO;
        this.DTO = new ResultDTO();
    }
    @Override
    public Result getResult(Integer groupId) {

        try{

            // get the result list
            List<Results> resultList = resultDAO.findResultByGroupId(groupId);

            // convert to VO list
            ResultResp resp = new ResultResp();
            List<ResultResp> respList = resp.convertPOJOListToRespList(resultList);

            // return the VO list
            return R.success(respList);

        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Result createSettlement(Integer groupId) {

        try{
            MemberGroupConnDTO dto = new MemberGroupConnDTO();
            // find all connection data in this group.
            List<MemberGroupConnDTO> connList = dto.convertPOJOListToDTOList(connDAO.findAllByGroupId(groupId));


            // split into two groups: payer (who should receive money) and ower (who should pay)

            List<MemberGroupConnDTO> takers = new ArrayList<>();
            List<MemberGroupConnDTO> givers = new ArrayList<>();

            for (MemberGroupConnDTO conn : connList){
                if (conn.getNet() > 0) takers.add(conn);
                else {
                    conn.setNet(conn.getNet() * (-1));
                    givers.add(conn);
                }
            }
            // make their net to absolute value and sort ASC
            takers.sort(null);
            givers.sort(null);

            int takerPointer = 0;
            int giverPointer = 0;
            double takerNet = takers.get(takerPointer).getNet();
            double giverNet = givers.get(giverPointer).getNet();

            List<ResultDTO> dtoList = new ArrayList<>();

            // compare the first two
            while(true){
                int takerId = takers.get(takerPointer).getMemberId();
                int giverId = givers.get(giverPointer).getMemberId();
                ResultDTO resultDTO = new ResultDTO();

                // if the payer's net is more than ower's, then payer's net minus ower's,
                // and use the same payer to compare the next ower.
                if (takerNet > giverNet){

                    addToResultList(groupId, giverNet, dtoList, takerId, giverId, resultDTO);

                    giverPointer++;
                    if (giverPointer > givers.size()-1) break;

                    takerNet = takerNet - giverNet;
                    giverNet = givers.get(giverPointer).getNet();

                    // if the payer's net is less than ower's, then ower's net minus payer's,
                    // and use the next payer to compare the same ower.
                }else if (giverNet > takerNet){

                    addToResultList(groupId, takerNet, dtoList, takerId, giverId, resultDTO);

                    takerPointer++;
                    if (takerPointer > takers.size()-1) break;

                    giverNet = giverNet - takerNet;
                    takerNet = takers.get(takerPointer).getNet();
                }else{

                    addToResultList(groupId, takerNet, dtoList, takerId, giverId, resultDTO);

                    takerPointer++;
                    giverPointer++;
                    if (takerPointer > takers.size()-1) break;
                    if (giverPointer > givers.size()-1) break;
                }
            }

            resultDAO.saveAll(DTO.convertDTOListToPOJOList(dtoList));

            // after saving results, change the redirect in group to true.
            Group group = groupDAO.getReferenceById(groupId);
            group.setRedirect(true);
            groupDAO.save(group);

            return R.success(null);
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }

    private void addToResultList(Integer groupId,
                                 double net,
                                 List<ResultDTO> dtoList,
                                 int payerId,
                                 int owerId,
                                 ResultDTO resultDTO) {

        resultDTO.setPayerId(payerId);
        resultDTO.setOwerId(owerId);
        resultDTO.setAmount(net);
        resultDTO.setGroupId(groupId);

        dtoList.add(resultDTO);
    }

    @Override
    @Transactional
    public Result switchResultStatusAndUpdateNet(Integer resultId) {

        try{
            Results result = resultDAO.getReferenceById(resultId);

            // update net in conn
            MemberGroupConn payerConn = connDAO.findByGroupIdAndMemberId(result.getGroupId(), result.getTakerId());
            MemberGroupConn owerConn = connDAO.findByGroupIdAndMemberId(result.getGroupId(), result.getGiverId());

            // if status == false means he's paying now,
            // so need to reduce payer's net and increase ower's
            if (!result.getStatus()){

                payerConn.setNet(payerConn.getNet() - result.getAmount());
                owerConn.setNet(owerConn.getNet() + result.getAmount());

            }else{

                payerConn.setNet(payerConn.getNet() + result.getAmount());
                owerConn.setNet(owerConn.getNet() - result.getAmount());

            }
            payerConn.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            owerConn.setUpdateTime(new Timestamp(System.currentTimeMillis()));


            result.setStatus(!result.getStatus());
            result.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            resultDAO.save(result);

            return R.success(null);
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }
}
