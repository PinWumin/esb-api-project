package com.esb.emp_seat_api.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.esb.emp_seat_api.model.dao.EmpInfoDAO;
import com.esb.emp_seat_api.model.vo.FloorDetailVO;
import com.esb.emp_seat_api.repository.EmpInfoRepository;

@Service
public class EmpInfoService {

    public List<EmpInfoDAO> getAllEmpInfo() {
        EmpInfoRepository empInfoRepository = new EmpInfoRepository();
        return empInfoRepository.getAllEmpInfo();
    }

    public List<EmpInfoDAO> getEmpInfoData(Boolean isSetSeat) {
        List<EmpInfoDAO> allEmpList = getAllEmpInfo();
        List<EmpInfoDAO> isSetEmpList = allEmpList.stream().filter(f -> f.getFloor_seat_seq() != 0)
                .collect(Collectors.toList());
        List<EmpInfoDAO> notSetEmpList = allEmpList.stream().filter(f -> f.getFloor_seat_seq() == 0)
                .collect(Collectors.toList());
        if (isSetSeat) {
            return isSetEmpList;
        } else {
            return notSetEmpList;
        }
    }

    public void editEmpFloorSeatSeq(int emp_id, int floor_seat_seq) {
        EmpInfoRepository empInfoRepository = new EmpInfoRepository();
        empInfoRepository.editEmpFloorSeatSeq(emp_id, floor_seat_seq);
    }

    public void editEmpFloorSeatSeqData(Map<Integer, List<FloorDetailVO>> editData) {
        for (Entry<Integer, List<FloorDetailVO>> mapItem : editData.entrySet()) {
            for (FloorDetailVO fdItem : mapItem.getValue()) {
                if (fdItem.getDel_id() != 0) {
                    editEmpFloorSeatSeq(fdItem.getDel_id(), 0);
                }
                if (fdItem.getTmp_id() != 0) {
                    editEmpFloorSeatSeq(fdItem.getTmp_id(), fdItem.getFloor_seat_seq());
                }

            }
        }
    }
}
