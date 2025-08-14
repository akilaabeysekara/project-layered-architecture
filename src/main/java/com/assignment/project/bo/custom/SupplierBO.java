package com.assignment.project.bo.custom;

import com.assignment.project.bo.SuperBO;
import com.assignment.project.dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierBO extends SuperBO {
    public String getNextSupplierId() throws SQLException;
    public ArrayList<String> getAllSupplierIds() throws SQLException;
    public SupplierDto findBySupplierId(String selectedSupplierId) throws SQLException;
    public boolean saveSupplier(SupplierDto supplierDto) throws SQLException;
    public boolean updateSupplier(SupplierDto supplierDto) throws SQLException;
    public boolean deleteSupplier(String supplierID) throws SQLException;
    public List<SupplierDto> getAllSupplier() throws SQLException;
    public String findNameBySupplierId(String supplierId) throws SQLException;
}
