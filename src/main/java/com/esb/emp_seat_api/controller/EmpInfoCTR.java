package com.esb.emp_seat_api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esb.emp_seat_api.model.dao.EmpInfoDAO;
import com.esb.emp_seat_api.model.vo.FloorDetailVO;
import com.esb.emp_seat_api.service.EmpInfoService;

@RestController
@RequestMapping("/rs/empInfo")
public class EmpInfoCTR {

    @Autowired
    private EmpInfoService empInfoService;

    @GetMapping("/getEmpInfoData/{isSetSeat}")
    public ResponseEntity<List<EmpInfoDAO>> getEmpInfoData(@PathVariable("isSetSeat") Boolean isSetSeat) {
        return ResponseEntity.ok().body(empInfoService.getEmpInfoData(isSetSeat));
    }

    @PostMapping("/editEmpFloorSeatSeqData")
    public void editEmpFloorSeatSeqData(@RequestBody Map<Integer, List<FloorDetailVO>> editData) {
        empInfoService.editEmpFloorSeatSeqData(editData);
    }
}
