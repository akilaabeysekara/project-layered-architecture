package com.assignment.AppliMax.bo.custom;

import com.assignment.AppliMax.bo.SuperBO;
import com.assignment.AppliMax.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeBO extends SuperBO {
    public String getNextEmployeeId() throws SQLException;
    public ArrayList<String> getAllEmployeeIds() throws SQLException;
    public EmployeeDto findByEmployeeId(String selectedEmId) throws SQLException;
    public boolean saveEmployee(EmployeeDto employeeDto) throws SQLException;
    public boolean updateEmployee(EmployeeDto employeeDto) throws SQLException;
    public boolean deleteEmployee(String employeeID) throws SQLException;
    public List<EmployeeDto> getAllEmployee() throws SQLException;
    public String getEmployeeName(String employeeId) throws SQLException;
    public String getEmployeeRole(String employeeId) throws SQLException;
}
