package com.assignment.project.dao.custom;

import com.assignment.project.dao.CrudDAO;
import com.assignment.project.entity.Material;

import java.sql.SQLException;

public interface MaterialDAO extends CrudDAO<Material> {
    String getUnit(String materialId) throws SQLException;
    String getName(String materialId) throws SQLException;
//    public String getNextMaterialId() throws SQLException;
//    public ArrayList<String> getAllMaterialIds() throws SQLException;
//    public boolean saveMaterial(MaterialDto materialDto) throws SQLException;
//    public boolean updateMaterial(MaterialDto materialDto) throws SQLException;
//    public boolean deleteMaterial(String materialId) throws SQLException;
//    public MaterialDto findById(String selectedMaterialId) throws SQLException;
//    public List<MaterialDto> getAllMaterials() throws SQLException;
//    public String getUnit(String materialId) throws SQLException;
//    public String getName(String materialId) throws SQLException;
}
