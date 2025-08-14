package com.assignment.project.dao.custom;

import com.assignment.project.dao.CrudDAO;
import com.assignment.project.entity.MaterialBuy;

import java.sql.SQLException;

public interface MaterialBuyDAO extends CrudDAO<MaterialBuy> {
    public boolean updateMaterialStock(String materialId, int quantity) throws SQLException;
}
