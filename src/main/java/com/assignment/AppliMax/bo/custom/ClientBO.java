package com.assignment.AppliMax.bo.custom;

import com.assignment.AppliMax.bo.SuperBO;
import com.assignment.AppliMax.dto.ClientDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ClientBO extends SuperBO {
    boolean saveClient(ClientDto clientDto) throws SQLException;
    boolean updateClient(ClientDto clientDto) throws SQLException;
    String getNextClientId() throws SQLException;
    public ClientDto findByClientId(String selectedId) throws SQLException;
    public ArrayList<String> getAllClientIds() throws SQLException;
    public boolean deleteClient(String ID) throws SQLException;
    public List<ClientDto> getAllClient() throws SQLException;
}
