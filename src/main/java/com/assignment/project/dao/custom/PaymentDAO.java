package com.assignment.project.dao.custom;

import com.assignment.project.dao.CrudDAO;
import com.assignment.project.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentDAO extends CrudDAO<Payment> {
    ArrayList<String> findAllIds() throws SQLException;
    String findNameById(String Id) throws SQLException;
    ArrayList<String> getAllIdsBy(String Id) throws SQLException;
}
