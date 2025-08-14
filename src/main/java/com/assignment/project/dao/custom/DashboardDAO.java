package com.assignment.project.dao.custom;

import com.assignment.project.dao.CrudDAO;
import com.assignment.project.view.tdm.ProjectExpensesTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DashboardDAO extends CrudDAO {
    public ArrayList<String> getAllProjectName() throws SQLException;
    public double getTotalIncome() throws SQLException;
    public double getAllExpenses() throws SQLException;
    public ArrayList<ProjectExpensesTM> getProjectDetails() throws SQLException;
}
