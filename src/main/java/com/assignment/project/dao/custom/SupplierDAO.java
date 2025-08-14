package com.assignment.project.dao.custom;

import com.assignment.project.dao.CrudDAO;
import com.assignment.project.entity.Supplier;

import java.sql.SQLException;

public interface SupplierDAO extends CrudDAO<Supplier> {
    String findNameById(String supplierId) throws SQLException;

//    public String getNextSupplierId() / getNextId() throws SQLException;
//    public ArrayList<String> getAllSupplierIds() / getAllIds() throws SQLException;
//    public SupplierDto findById(String selectedSupplierId) throws SQLException;
//    public boolean saveSupplier(SupplierDto supplierDto) throws SQLException;
//    public boolean updateSupplier(SupplierDto supplierDto) throws SQLException;
//    public boolean deleteSupplier(String supplierID) throws SQLException;
//    public List<SupplierDto> getAllSuppliers() throws SQLException;
//    public String getSupplierNameById(String supplierId) / findNameById(String supplierId) throws SQLException;
}
