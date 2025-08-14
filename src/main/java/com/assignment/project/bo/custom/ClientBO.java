package com.assignment.project.bo.custom;

import com.assignment.project.bo.SuperBO;
import com.assignment.project.dto.ClientDto;

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
