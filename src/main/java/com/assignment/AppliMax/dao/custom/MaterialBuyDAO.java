package com.assignment.AppliMax.dao.custom;

import com.assignment.AppliMax.dao.CrudDAO;
import com.assignment.AppliMax.entity.MaterialBuy;

import java.sql.SQLException;

public interface MaterialBuyDAO extends CrudDAO<MaterialBuy> {
    public boolean updateMaterialStock(String materialId, int quantity) throws SQLException;
}
