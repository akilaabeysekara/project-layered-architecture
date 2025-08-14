package com.assignment.project.dao.custom.impl;

import com.assignment.project.dao.custom.MachineDAO;
import com.assignment.project.dao.SQLUtil;
import com.assignment.project.entity.Machine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MachineDAOImpl implements MachineDAO {

    // Pattern: any non-digits as prefix, then trailing digits (at least one)
    private static final Pattern ID_PATTERN = Pattern.compile("^(?<prefix>\\D*)(?<num>\\d+)$");

    public String getNextId() throws SQLException {
        // Limit the candidates to Machine IDs (assumed to start with 'M')
        ResultSet rst = SQLUtil.execute("SELECT Machine_ID FROM Machine WHERE Machine_ID LIKE 'M%' ORDER BY Machine_ID DESC LIMIT 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            Matcher matcher = ID_PATTERN.matcher(lastId);

            if (matcher.matches()) {
                String prefix = matcher.group("prefix"); // e.g., "M" or "MC"
                String numPart = matcher.group("num");   // e.g., "005"
                int width = numPart.length();

                int current = Integer.parseInt(numPart);
                int next = current + 1;

                // Preserve zero-padding width
                String nextNum = String.format("%0" + width + "d", next);
                return prefix + nextNum;
            } else {
                // If the format is unexpected, start fresh with a default
                return "M001";
            }
        }
        // No rows in table for Machine prefix
        return "M001";
    }

    public ArrayList<String> getAllIds() throws SQLException {
        ResultSet rst = SQLUtil.execute("select Machine_ID from Machine");
        ArrayList<String> machineIds = new ArrayList<>();

        while (rst.next()){
            machineIds.add(rst.getString(1));
        }

        return machineIds;
    }

    public Machine findById(String selectedMachineId) throws SQLException {
        ResultSet rst = SQLUtil.execute("select * from Machine where Machine_ID=?", selectedMachineId);

        if (rst.next()) {
            return new Machine(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
        }
        return null;
    }

    public boolean save(Machine entity) throws SQLException {
        return SQLUtil.execute(
                "insert into Machine values (?,?,?,?)",
                entity.getMachineId(),
                entity.getName(),
                entity.getStatus(),
                entity.getDescription()
        );
    }

    public boolean update(Machine entity) throws SQLException {
        String sql = "update Machine set Name=?, Status=?, Description=? where Machine_ID=?";
        return SQLUtil.execute(sql, entity.getName(), entity.getStatus(), entity.getDescription(), entity.getMachineId());
    }

    public boolean delete(String machineID) throws SQLException {
        String sql = "DELETE FROM Machine WHERE Machine_ID=?";
        return SQLUtil.execute(sql, machineID);
    }

    public List<Machine> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Machine");
        List<Machine> machineList = new ArrayList<>();

        while (resultSet.next()) {
            machineList.add(new Machine(
                    resultSet.getString("Machine_ID"),
                    resultSet.getString("Name"),
                    resultSet.getString("Status"),
                    resultSet.getString("Description")
            ));
        }
        return machineList;
    }

}
