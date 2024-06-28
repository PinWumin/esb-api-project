package com.esb.emp_seat_api.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.esb.emp_seat_api.model.JPAUtil;
import com.esb.emp_seat_api.model.dao.FloorInfoDAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.StoredProcedureQuery;

public class FloorInfoRepository {

    public List<FloorInfoDAO> getAllFloorInfo() {
        List<FloorInfoDAO> list = new ArrayList<>();
        EntityManager entityManager = JPAUtil.getEM();
        EntityTransaction trans = entityManager.getTransaction();
        try {
            trans.begin();
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("getAllFloorInfo");

            storedProcedure.execute();

            @SuppressWarnings("unchecked")
            List<Object[]> results = storedProcedure.getResultList();

            list = results.stream()
                    .map(row -> {
                        FloorInfoDAO item = new FloorInfoDAO();
                        item.setFloor_seat_seq((int) row[0]);
                        item.setFloor_no((int) row[1]);
                        item.setSeat_no((int) row[2]);
                        return item;
                    })
                    .collect(Collectors.toList());
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        } finally {
            if (trans.isActive()) {
                trans.rollback();
            }
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }

        return list;
    }

}
