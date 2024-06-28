package com.esb.emp_seat_api.model.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FloorVO {
    private int floor_no;
    private List<FloorDetailVO> floor_info;
}
