package com.esb.emp_seat_api.model.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "floor_info")
public class FloorInfoDAO {
    @Id
    private int floor_seat_seq;
    private int floor_no;
    private int seat_no;
}
