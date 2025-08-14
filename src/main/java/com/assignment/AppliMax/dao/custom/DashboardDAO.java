package com.assignment.AppliMax.dao.custom;

import com.assignment.AppliMax.dao.CrudDAO;
import com.assignment.AppliMax.view.tdm.ProjectExpensesTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DashboardDAO extends CrudDAO {
    public ArrayList<String> getAllProjectName() throws SQLException;
    public double getTotalIncome() throws SQLException;
    public double getAllExpenses() throws SQLException;
    public ArrayList<ProjectExpensesTM> getProjectDetails() throws SQLException;
}
