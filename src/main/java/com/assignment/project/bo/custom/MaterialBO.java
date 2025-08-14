package com.assignment.project.bo.custom;

import com.assignment.project.bo.SuperBO;
import com.assignment.project.dto.MaterialDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface MaterialBO extends SuperBO {
    String getNextMaterialId() throws SQLException;
    ArrayList<String> getAllMaterialIds() throws SQLException;
    boolean saveMaterial(MaterialDto materialDto) throws SQLException;
    boolean updateMaterial(MaterialDto materialDto) throws SQLException;
    boolean deleteMaterial(String materialId) throws SQLException;
    MaterialDto findByMaterialId(String selectedMaterialId) throws SQLException;
    List<MaterialDto> getAllMaterial() throws SQLException;
    String getMaterialUnit(String materialId) throws SQLException;
    String getMaterialName(String materialId) throws SQLException;
}
