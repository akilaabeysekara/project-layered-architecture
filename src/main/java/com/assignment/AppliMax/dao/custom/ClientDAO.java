package com.assignment.AppliMax.dao.custom;

import com.assignment.AppliMax.dao.CrudDAO;
import com.assignment.AppliMax.entity.Client;

import java.sql.SQLException;


public interface ClientDAO extends CrudDAO<Client> {
    int getClientCount() throws SQLException;
}