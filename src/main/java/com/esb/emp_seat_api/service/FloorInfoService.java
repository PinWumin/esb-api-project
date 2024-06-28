package com.esb.emp_seat_api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.esb.emp_seat_api.model.dao.EmpInfoDAO;
import com.esb.emp_seat_api.model.dao.FloorInfoDAO;
import com.esb.emp_seat_api.model.vo.FloorDetailVO;
import com.esb.emp_seat_api.repository.FloorInfoRepository;

@Service
public class FloorInfoService {

    public List<FloorInfoDAO> getAllFloorInfo() {
        FloorInfoRepository floorInfoRepository = new FloorInfoRepository();
        return floorInfoRepository.getAllFloorInfo();
    }

    public Map<Integer, List<FloorDetailVO>> getAllFloorInfoData() {
        List<FloorInfoDAO> floorInfoList = getAllFloorInfo();
        Map<Integer, List<FloorDetailVO>> floorMap = new HashMap<>();

        for (FloorInfoDAO item : floorInfoList) {
            if (floorMap.size() == 0) {
                List<FloorDetailVO> detailList = new ArrayList<>();
                FloorDetailVO floorDetailVO = new FloorDetailVO();
                floorDetailVO.setFloor_seat_seq(item.getFloor_seat_seq());
                floorDetailVO.setSeat_no(item.getSeat_no());
                floorDetailVO.setTmp_id(0);
                floorDetailVO.setEmp_id(0);
                floorDetailVO.setDel_id(0);
                detailList.add(floorDetailVO);
                floorMap.put(item.getFloor_no(), detailList);
            } else {
                if (floorMap.containsKey(item.getFloor_no())) {
                    List<FloorDetailVO> detailList = floorMap.get(item.getFloor_no());
                    FloorDetailVO floorDetailVO = new FloorDetailVO();
                    floorDetailVO.setFloor_seat_seq(item.getFloor_seat_seq());
                    floorDetailVO.setSeat_no(item.getSeat_no());
                    floorDetailVO.setTmp_id(0);
                    floorDetailVO.setEmp_id(0);
                    floorDetailVO.setDel_id(0);
                    detailList.add(floorDetailVO);
                    // floorMap.put(item.getFloor_no(), detailList);
                } else {
                    List<FloorDetailVO> detailList = new ArrayList<>();
                    FloorDetailVO floorDetailVO = new FloorDetailVO();
                    floorDetailVO.setFloor_seat_seq(item.getFloor_seat_seq());
                    floorDetailVO.setSeat_no(item.getSeat_no());
                    floorDetailVO.setTmp_id(0);
                    floorDetailVO.setEmp_id(0);
                    floorDetailVO.setDel_id(0);
                    detailList.add(floorDetailVO);
                    floorMap.put(item.getFloor_no(), detailList);
                }
            }

        }
        EmpInfoService empInfoService = new EmpInfoService();
        List<EmpInfoDAO> empInfoList = empInfoService.getAllEmpInfo();

        for (EmpInfoDAO item : empInfoList) {
            if (item.getFloor_seat_seq() != 0) {
                for (Entry<Integer, List<FloorDetailVO>> mapItem : floorMap.entrySet()) {
                    for (FloorDetailVO fdItem : mapItem.getValue()) {
                        if (item.getFloor_seat_seq() == fdItem.getFloor_seat_seq()) {
                            fdItem.setEmp_id(item.getEmp_id());
                        }
                    }
                }
            }
        }
        return floorMap;
    }
}
