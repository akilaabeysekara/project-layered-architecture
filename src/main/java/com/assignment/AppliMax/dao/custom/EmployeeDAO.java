package com.assignment.AppliMax.dao.custom;

import com.assignment.AppliMax.dao.CrudDAO;
import com.assignment.AppliMax.entity.Employee;

import java.sql.SQLException;


public interface EmployeeDAO extends CrudDAO<Employee> {
    String getName(String employeeId) throws SQLException;
    String getInfo(String employeeId) throws SQLException;
}
