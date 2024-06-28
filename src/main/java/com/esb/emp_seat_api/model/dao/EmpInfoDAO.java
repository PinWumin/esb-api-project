package com.esb.emp_seat_api.model.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "emp_info")
public class EmpInfoDAO {
    @Id
    private int emp_id;
    private String name;
    private String email;
    private int floor_seat_seq;
}
