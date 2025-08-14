package com.assignment.project.bo.custom;

import com.assignment.project.bo.SuperBO;
import com.assignment.project.dto.MaterialUsageDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface MaterialUsageBO extends SuperBO {
    public String getNextMaterialUsageId() throws SQLException;
    public MaterialUsageDto findByMaterialUsageId(String selectedId) throws SQLException;
    public ArrayList<String> getAllMaterialUsageIds() throws SQLException;
    public boolean saveMaterialUsage(MaterialUsageDto materialUsageDto) throws SQLException;
    public boolean updateMaterialUsage(MaterialUsageDto materialUsageDto) throws SQLException;
    public List<MaterialUsageDto> getAllMaterialUsage() throws SQLException;
    public boolean deleteMaterialUsage(String usageId) throws SQLException;
}
