package com.assignment.project.dao.custom.impl;

import com.assignment.project.dao.custom.MaterialDAO;
import com.assignment.project.dao.SQLUtil;
import com.assignment.project.entity.Material;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAOImpl implements MaterialDAO {
    public String getNextId() throws SQLException {
        ResultSet rst = SQLUtil.execute("select Material_ID from Material order by Material_ID desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(2);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("MA%03d", newIdIndex);
        }
        return "MA001";
    }

    public ArrayList<String> getAllIds() throws SQLException {
        ResultSet rst = SQLUtil.execute("select Material_ID from Material");
        ArrayList<String> materialIds = new ArrayList<>();

        while (rst.next()) {
            materialIds.add(rst.getString(1));
        }
        return materialIds;
    }

    public boolean save(Material entity) throws SQLException {
        return SQLUtil.execute(
                "insert into Material values (?,?,?,?)",
                entity.getMaterialId(),
                entity.getName(),
                entity.getQty(),
                entity.getUnit()
        );
    }

    public boolean update(Material entity) throws SQLException {
        String sql = "update Material set Name=?, Quantity_in_Stock=?, Unit=? where Material_ID=?";
        return SQLUtil.execute(sql, entity.getName(), entity.getQty(), entity.getUnit(), entity.getMaterialId());
    }

    public boolean delete(String materialId) throws SQLException {
        String sql = "DELETE FROM Material WHERE Material_ID=?";
        return SQLUtil.execute(sql, materialId);
    }

    public Material findById(String selectedMaterialId) throws SQLException {
        ResultSet rst = SQLUtil.execute("select * from Material where Material_ID=?", selectedMaterialId);
        if (rst.next()){
            return new Material(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
        }
        return null;
    }

    public List<Material> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Material");
        List<Material> materialList = new ArrayList<>();

        while (rst.next()) {
            materialList.add(new Material(
                    rst.getString("Material_ID"),
                    rst.getString("Name"),
                    rst.getString("Quantity_in_Stock"),
                    rst.getString("Unit")
            ));
        }
        return materialList;
    }

    public String getUnit(String materialId) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT Unit FROM Material where Material_ID=?", materialId);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    public String getName(String materialId) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT Name FROM Material where Material_ID=?", materialId);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

}
