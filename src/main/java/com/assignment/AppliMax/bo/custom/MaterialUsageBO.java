package com.assignment.AppliMax.bo.custom;

import com.assignment.AppliMax.bo.SuperBO;
import com.assignment.AppliMax.dto.MaterialUsageDto;

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
