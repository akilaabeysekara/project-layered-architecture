package com.assignment.AppliMax.bo.custom;

import com.assignment.AppliMax.bo.SuperBO;
import com.assignment.AppliMax.dto.MaterialBuyDto;

import java.sql.SQLException;
import java.util.List;

public interface MaterialBuyBO extends SuperBO {
    String getNextMaterialBuyId() throws SQLException;
    List<MaterialBuyDto> getAllMaterialBuy() throws SQLException;
    boolean saveMaterialBuy(MaterialBuyDto materialBuyDto) throws SQLException;
    boolean updateMaterialBuy(MaterialBuyDto materialBuyDto) throws SQLException;
    boolean deleteMaterialBuy(String paymentId) throws SQLException;
}
