package com.assignment.AppliMax.dao.custom;

import com.assignment.AppliMax.dao.CrudDAO;
import com.assignment.AppliMax.entity.MaterialUsage;

import java.sql.SQLException;

public interface MaterialUsageDAO extends CrudDAO<MaterialUsage> {
    public int getMaterialStock(String materialId) throws SQLException;
    public boolean updateMaterialStock(String materialId, int quantity) throws SQLException;

}
