package com.assignment.project.dao.custom;

import com.assignment.project.dao.CrudDAO;
import com.assignment.project.entity.Employee;

import java.sql.SQLException;


public interface EmployeeDAO extends CrudDAO<Employee> {
    String getName(String employeeId) throws SQLException;
    String getInfo(String employeeId) throws SQLException;
}
