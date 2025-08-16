package com.assignment.project.bo.custom.impl;

import com.assignment.project.bo.custom.ClientBO;
import com.assignment.project.bo.exception.DuplicateException;
import com.assignment.project.bo.exception.InUseException;
import com.assignment.project.bo.exception.NotFoundException;
import com.assignment.project.dao.DAOFactory;
import com.assignment.project.dao.custom.impl.ClientDAOImpl;
import com.assignment.project.dto.ClientDto;
import com.assignment.project.entity.Client;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientBOImpl implements ClientBO {
    ClientDAOImpl clientDAO = (ClientDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CLIENT);

    public boolean saveClient(ClientDto clientDto) throws SQLException{
        // Check for duplicate client ID before saving
        Client existing = clientDAO.findById(clientDto.getId());
        if (existing != null) {
            throw new DuplicateException("Client with id " + clientDto.getId() + " already exists");
        }
        return clientDAO.save(new Client(clientDto.getId(), clientDto.getName(), clientDto.getAddress(), clientDto.getPhoneNo(), clientDto.getEmail()));
    }

    public boolean updateClient(ClientDto clientDto) throws SQLException{
        // Ensure client exists before update
        Client existing = clientDAO.findById(clientDto.getId());
        if (existing == null) {
            throw new NotFoundException("Client not found");
        }
        return clientDAO.update(new Client(clientDto.getId(), clientDto.getName(), clientDto.getAddress(), clientDto.getPhoneNo(), clientDto.getEmail()));
    }

    public String getNextClientId() throws SQLException{
        return clientDAO.getNextId();
    }

    public ClientDto findByClientId(String selectedId) throws SQLException{
        Client client = clientDAO.findById(selectedId);
        if (client == null) {
            throw new NotFoundException("Client not found");
        }
        return new ClientDto(client.getId(), client.getName(), client.getAddress(), client.getPhoneNo(), client.getEmail());
    }

    public ArrayList<String> getAllClientIds() throws SQLException {
        return clientDAO.getAllIds();
    }

    public boolean deleteClient(String ID) throws SQLException {
        // Ensure client exists first
        Client existing = clientDAO.findById(ID);
        if (existing == null) {
            throw new NotFoundException("Client not found");
        }

        // Try delete; if DAO reports failure, treat as "in use"
        boolean deleted = clientDAO.delete(ID);
        if (!deleted) {
            throw new InUseException("Client is in use and cannot be deleted");
        }
        return true;
    }

    public List<ClientDto> getAllClient() throws SQLException {
        List<Client> clients = clientDAO.getAll();
        List<ClientDto> clientList = new ArrayList<>();
        for (Client client : clients) {
            clientList.add(new ClientDto(client.getId(), client.getName(), client.getAddress(), client.getPhoneNo(), client.getEmail()));
        }
        return clientList;
    }

    public int getCount() throws SQLException {
        return clientDAO.getClientCount();
    }

//    public String getName(String Id) throws SQLException {
//        return clientDAO.getName(Id);
//    }
//
//    public String getInfo(String Id) throws SQLException {
//        return clientDAO.getInfo(Id);
//    }
//
//    public String getUnit(String Id) throws SQLException {
//        return clientDAO.getUnit(Id);
//    }
//
//    public ArrayList<String> findAllIds() throws SQLException {
//        return clientDAO.findAllIds();
//    }
//
//    public String findNameById(String Id) throws SQLException {
//        return clientDAO.findNameById(Id);
//    }
//
//    public ArrayList<String> getAllIdsBy(String Id) throws SQLException {
//        return clientDAO.getAllIdsBy(Id);
//    }
}
