package com.assignment.project.dao.custom.impl;

import com.assignment.project.dao.custom.MaterialBuyDAO;
import com.assignment.project.dao.SQLUtil;
import com.assignment.project.entity.MaterialBuy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaterialBuyDAOImpl implements MaterialBuyDAO {

    private static final Pattern TRAILING_DIGITS = Pattern.compile("(\\d+)$");

    @Override
    public String getNextId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT Payment_ID FROM MaterialBuy ORDER BY Payment_ID DESC LIMIT 1");
        if (rst.next()) {
            String lastId = rst.getString(1);
            if (lastId == null || lastId.isBlank()) {
                return "MB001";
            }
            lastId = lastId.trim();

            Matcher matcher = TRAILING_DIGITS.matcher(lastId);
            String prefix = lastId.replaceAll("\\d", "");
            int nextIndex = 1;

            if (matcher.find()) {
                String digits = matcher.group(1);
                // Preserve actual prefix before the trailing digits
                prefix = lastId.substring(0, lastId.length() - digits.length());
                nextIndex = Integer.parseInt(digits) + 1;
            }

            return String.format("%s%03d", prefix, nextIndex);
        }
        return "MB001";
    }

    @Override
    public MaterialBuy findById(String selectedId) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM MaterialBuy WHERE Payment_ID = ?", selectedId);
        if (rst.next()) {
            return new MaterialBuy(
                    rst.getString("Payment_ID"),
                    rst.getString("Material_ID"),
                    rst.getString("Supplier_ID"),
                    rst.getDate("Date"),
                    rst.getDouble("Unit_Amount"),
                    rst.getString("Quantity"),
                    rst.getDouble("Total_Price")
            );
        }
        return null;
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT Payment_ID FROM MaterialBuy");
        ArrayList<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(rst.getString(1));
        }
        return ids;
    }

    @Override
    public List<MaterialBuy> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM MaterialBuy");
        List<MaterialBuy> purchaseList = new ArrayList<>();
        while (rst.next()) {
            purchaseList.add(new MaterialBuy(
                    rst.getString("Payment_ID"),
                    rst.getString("Material_ID"),
                    rst.getString("Supplier_ID"),
                    rst.getDate("Date"),
                    rst.getDouble("Unit_Amount"),
                    rst.getString("Quantity"),
                    rst.getDouble("Total_Price")
            ));
        }
        return purchaseList;
    }

    @Override
    public boolean save(MaterialBuy entity) throws SQLException {
        return SQLUtil.execute(
                "INSERT INTO MaterialBuy VALUES (?,?,?,?,?,?,?)",
                entity.getPaymentId(),
                entity.getMaterialId(),
                entity.getSupplierId(),
                entity.getDate(),
                entity.getUnitAmount(),
                entity.getQuantity(),
                entity.getTotalPrice()
        );
    }

    @Override
    public boolean update(MaterialBuy entity) throws SQLException {
        return SQLUtil.execute(
                "UPDATE MaterialBuy SET Material_ID=?, Supplier_ID=?, Date=?, Unit_Amount=?, Quantity=?, Total_Price=? WHERE Payment_ID=?",
                entity.getMaterialId(),
                entity.getSupplierId(),
                entity.getDate(),
                entity.getUnitAmount(),
                entity.getQuantity(),
                entity.getTotalPrice(),
                entity.getPaymentId()
        );
    }

    @Override
    public boolean delete(String paymentId) throws SQLException {
        return SQLUtil.execute("DELETE FROM MaterialBuy WHERE Payment_ID=?", paymentId);
    }

    @Override
    public boolean updateMaterialStock(String materialId, int quantity) throws SQLException {
        return SQLUtil.execute(
                "UPDATE Material SET Quantity_in_Stock = Quantity_in_Stock + ? WHERE Material_ID = ?",
                quantity,
                materialId
        );
    }
}