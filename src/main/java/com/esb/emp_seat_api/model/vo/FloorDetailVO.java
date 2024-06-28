package com.esb.emp_seat_api.model.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FloorDetailVO {
    private int floor_seat_seq;
    private int seat_no;
    private int emp_id;
    private int tmp_id;
    private int del_id;
}