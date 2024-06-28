package com.esb.emp_seat_api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esb.emp_seat_api.model.vo.FloorDetailVO;
import com.esb.emp_seat_api.service.FloorInfoService;

@RestController
@RequestMapping("/rs/floorInfo")
public class FloorInfoCTR {

    @Autowired
    private FloorInfoService floorInfoService;

    @GetMapping("/getAllFloorInfoData")
    public ResponseEntity<Map<Integer, List<FloorDetailVO>>> getAllFloorInfoData() {
        return ResponseEntity.ok().body(floorInfoService.getAllFloorInfoData());
    }
}
