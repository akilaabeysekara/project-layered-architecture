package com.assignment.AppliMax.bo.custom;

import com.assignment.AppliMax.bo.SuperBO;
import com.assignment.AppliMax.dto.MachineDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface MachineBO extends SuperBO {
    public String getNextMachineId() throws SQLException;
    public ArrayList<String> getAllMachineIds() throws SQLException;
    public MachineDto findByMachineId(String selectedMachineId) throws SQLException;
    public boolean saveMachine(MachineDto machineDto) throws SQLException;
    public boolean updateMachine(MachineDto machineDto) throws SQLException;
    public boolean deleteMachine(String machineID) throws SQLException;
    public List<MachineDto> getAllMachine() throws SQLException;
}
