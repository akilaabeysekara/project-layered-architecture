package com.assignment.project.dao.custom;

import com.assignment.project.dao.CrudDAO;
import com.assignment.project.entity.Client;

import java.sql.SQLException;


public interface ClientDAO extends CrudDAO<Client> {
    int getClientCount() throws SQLException;
}