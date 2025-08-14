package com.assignment.project.dao.custom;

import com.assignment.project.dao.CrudDAO;
import com.assignment.project.entity.MaterialUsage;

import java.sql.SQLException;

public interface MaterialUsageDAO extends CrudDAO<MaterialUsage> {
    public int getMaterialStock(String materialId) throws SQLException;
    public boolean updateMaterialStock(String materialId, int quantity) throws SQLException;

}
