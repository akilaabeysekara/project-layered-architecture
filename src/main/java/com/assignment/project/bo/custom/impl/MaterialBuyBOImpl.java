package com.assignment.project.bo.custom.impl;

import com.assignment.project.bo.custom.MaterialBuyBO;
import com.assignment.project.dao.DAOFactory;
import com.assignment.project.dao.custom.MaterialBuyDAO;
import com.assignment.project.db.DBConnection;
import com.assignment.project.entity.MaterialBuy;
import com.assignment.project.dto.MaterialBuyDto;
import com.assignment.project.bo.util.EntityDTOConverter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaterialBuyBOImpl implements MaterialBuyBO {

    private static final Pattern TRAILING_DIGITS = Pattern.compile("(\\d+)$");
    private final MaterialBuyDAO materialBuyDAO = (MaterialBuyDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MATERIALBUY);
    private final EntityDTOConverter converter = new EntityDTOConverter();

    @Override
    public String getNextMaterialBuyId() throws SQLException {
        return materialBuyDAO.getNextId();
    }

    private int extractTrailingNumber(String id) {
        if (id == null) return 0;
        String trimmed = id.trim();
        Matcher m = TRAILING_DIGITS.matcher(trimmed);
        if (m.find()) {
            return Integer.parseInt(m.group(1));
        }
        return 0;
    }

    private MaterialBuyDto convertToDto(MaterialBuy entity) {
        return new MaterialBuyDto(
                entity.getPaymentId(),
                entity.getMaterialId(),
                entity.getSupplierId(),
                entity.getDate(),
                entity.getUnitAmount(),
                entity.getQuantity(),
                entity.getTotalPrice()
        );
    }

    private MaterialBuy convertToEntity(MaterialBuyDto dto) {
        return new MaterialBuy(
                dto.getPaymentId(),
                dto.getMaterialId(),
                dto.getSupplierId(),
                dto.getDate(),
                dto.getUnitAmount(),
                dto.getQuantity(),
                dto.getTotalPrice()
        );
    }

    @Override
    public List<MaterialBuyDto> getAllMaterialBuy() throws SQLException {
        List<MaterialBuy> all = materialBuyDAO.getAll();
        List<MaterialBuyDto> materialBuyDtos = new ArrayList<>();
        for (MaterialBuy materialBuy : all) {
            materialBuyDtos.add(converter.getMaterialBuyDto(materialBuy));
        }
        return materialBuyDtos;
    }

    @Override
    public boolean saveMaterialBuy(MaterialBuyDto dto) throws SQLException {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            MaterialBuy materialBuy = converter.getMaterialBuy(dto);
            boolean isSaved = materialBuyDAO.save(materialBuy);

            if (!isSaved) {
                connection.rollback();
                return false;
            }

            boolean isUpdated = materialBuyDAO.updateMaterialStock(
                    dto.getMaterialId(),
                    Integer.parseInt(dto.getQuantity())
            );

            if (!isUpdated) {
                connection.rollback();
                return false;
            }

            connection.commit();
            return true;

        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
            }
        }
    }

    @Override
    public boolean updateMaterialBuy(MaterialBuyDto dto) throws SQLException {
        MaterialBuy materialBuy = converter.getMaterialBuy(dto);
        return materialBuyDAO.update(materialBuy);
    }

    @Override
    public boolean deleteMaterialBuy(String paymentId) throws SQLException {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            MaterialBuy materialBuy = materialBuyDAO.findById(paymentId);
            if (materialBuy == null) {
                connection.rollback();
                return false;
            }

            boolean isDeleted = materialBuyDAO.delete(paymentId);
            if (!isDeleted) {
                connection.rollback();
                return false;
            }

            boolean isUpdated = materialBuyDAO.updateMaterialStock(
                    materialBuy.getMaterialId(),
                    -Integer.parseInt(materialBuy.getQuantity())
            );

            if (!isUpdated) {
                connection.rollback();
                return false;
            }

            connection.commit();
            return true;

        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
            }
        }
    }
}