package com.esb.emp_seat_api.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.esb.emp_seat_api.model.JPAUtil;
import com.esb.emp_seat_api.model.dao.EmpInfoDAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;

public class EmpInfoRepository {

    public List<EmpInfoDAO> getAllEmpInfo() {
        List<EmpInfoDAO> list = new ArrayList<>();
        EntityManager entityManager = JPAUtil.getEM();
        EntityTransaction trans = entityManager.getTransaction();
        try {
            trans.begin();

            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("getAllEmpInfo");

            storedProcedure.execute();

            @SuppressWarnings("unchecked")
            List<Object[]> results = storedProcedure.getResultList();
            list = results.stream()
                    .map(row -> {
                        EmpInfoDAO item = new EmpInfoDAO();
                        item.setEmp_id((int) row[0]);
                        item.setName((String) row[1]);
                        item.setEmail((String) row[2]);
                        item.setFloor_seat_seq(row[3] == null ? 0 : (int) row[3]);
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

    public void editEmpFloorSeatSeq(int emp_id, int floor_seat_seq) {
        EntityManager entityManager = JPAUtil.getEM();
        EntityTransaction trans = entityManager.getTransaction();
        try {
            trans.begin();
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("editEmpFloorSeatSeq");

            storedProcedure.registerStoredProcedureParameter(1, Integer.class,
                    ParameterMode.IN);
            storedProcedure.setParameter(1, emp_id);

            storedProcedure.registerStoredProcedureParameter(2, Integer.class,
                    ParameterMode.IN);
            storedProcedure.setParameter(2, floor_seat_seq);

            storedProcedure.execute();

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
    }

}
