package com.assignment.AppliMax.bo.custom;

import com.assignment.AppliMax.bo.SuperBO;
import com.assignment.AppliMax.dto.ExpensesDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ExpensesBO extends SuperBO {
    String getNextExpensesId() throws SQLException;
    public ArrayList<String> getAllExpensesIds() throws SQLException;
    public ExpensesDto findByExpensesId(String selectedExpenseId) throws SQLException;
    public boolean saveExpenses(ExpensesDto expensesDto) throws SQLException;
    public boolean updateExpenses(ExpensesDto expensesDto) throws SQLException;
    public boolean deleteExpenses(String expenseId) throws SQLException;
    public List<ExpensesDto> getAllExpenses() throws SQLException;
}
