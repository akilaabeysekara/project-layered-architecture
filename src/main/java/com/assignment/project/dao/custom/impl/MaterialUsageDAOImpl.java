package com.assignment.project.dao.custom.impl;

import com.assignment.project.dao.custom.MaterialUsageDAO;
import com.assignment.project.dao.SQLUtil;
import com.assignment.project.entity.MaterialUsage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaterialUsageDAOImpl implements MaterialUsageDAO {
    private static final Pattern TRAILING_DIGITS = Pattern.compile("(\\d+)$");

    @Override
    public String getNextId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT Usage_ID FROM MaterialUsage ORDER BY Usage_ID DESC LIMIT 1");
        if (rst.next()) {
            String lastId = rst.getString(1);
            if (lastId == null || lastId.isBlank()) {
                return "MU001";
            }
            lastId = lastId.trim();

            Matcher matcher = TRAILING_DIGITS.matcher(lastId);
            String prefix = lastId.replaceAll("\\d", "");
            int nextIndex = 1;

            if (matcher.find()) {
                String digits = matcher.group(1);
                prefix = lastId.substring(0, lastId.length() - digits.length());
                nextIndex = Integer.parseInt(digits) + 1;
            }

            return String.format("%s%03d", prefix, nextIndex);
        }
        return "MU001";
    }

    @Override
    public MaterialUsage findById(String selectedId) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM MaterialUsage WHERE Usage_ID = ?", selectedId);
        if (rst.next()) {
            return new MaterialUsage(
                    rst.getString("Usage_ID"),
                    rst.getString("Project_ID"),
                    rst.getString("Material_ID"),
                    rst.getString("Quantity_used"),
                    rst.getString("Date")
            );
        }
        return null;
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT Usage_ID FROM MaterialUsage ORDER BY Usage_ID");
        ArrayList<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(rst.getString(1));
        }
        return ids;
    }

    @Override
    public boolean save(MaterialUsage entity) throws SQLException {
        return SQLUtil.execute(
                "INSERT INTO MaterialUsage (Usage_ID, Project_ID, Material_ID, Quantity_used, Date) VALUES (?,?,?,?,?)",
                entity.getUsageId(),
                entity.getProjectId(),
                entity.getMaterialId(),
                entity.getQuantityUsed(),
                entity.getDate()
        );
    }

    @Override
    public boolean update(MaterialUsage entity) throws SQLException {
        return SQLUtil.execute(
                "UPDATE MaterialUsage SET Project_ID=?, Material_ID=?, Quantity_used=?, Date=? WHERE Usage_ID=?",
                entity.getProjectId(),
                entity.getMaterialId(),
                entity.getQuantityUsed(),
                entity.getDate(),
                entity.getUsageId()
        );
    }

    @Override
    public List<MaterialUsage> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM MaterialUsage ORDER BY Usage_ID");
        List<MaterialUsage> usageList = new ArrayList<>();
        while (rst.next()) {
            usageList.add(new MaterialUsage(
                    rst.getString("Usage_ID"),
                    rst.getString("Project_ID"),
                    rst.getString("Material_ID"),
                    rst.getString("Quantity_used"),
                    rst.getString("Date")
            ));
        }
        return usageList;
    }

    @Override
    public boolean delete(String usageId) throws SQLException {
        return SQLUtil.execute("DELETE FROM MaterialUsage WHERE Usage_ID=?", usageId);
    }

    @Override
    public int getMaterialStock(String materialId) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT Quantity_in_Stock FROM Material WHERE Material_ID = ?", materialId);
        if (rst.next()) {
            return rst.getInt("Quantity_in_Stock");
        }
        return 0;
    }

    @Override
    public boolean updateMaterialStock(String materialId, int quantity) throws SQLException {
        return SQLUtil.execute(
                "UPDATE Material SET Quantity_in_Stock = GREATEST(0, Quantity_in_Stock + ?) WHERE Material_ID = ?",
                quantity,
                materialId
        );
    }
}