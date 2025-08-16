package com.assignment.project.bo.util;

import com.assignment.project.dto.ClientDto;
import com.assignment.project.entity.Client;
import com.assignment.project.dto.MaterialBuyDto;
import com.assignment.project.entity.MaterialBuy;

public class EntityDTOConverter {
    public ClientDto getClientDTO(Client client) {
        ClientDto dto = new ClientDto();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setAddress(client.getAddress());
        dto.setPhoneNo(client.getPhoneNo());
        dto.setEmail(client.getEmail());
        return dto;
    }

    public Client getClient(ClientDto dto) {
        Client client = new Client();
        client.setId(dto.getId());
        client.setName(dto.getName());
        client.setAddress(dto.getAddress());
        client.setPhoneNo(dto.getPhoneNo());
        client.setEmail(dto.getEmail());
        return client;
    }

    // MaterialBuy <-> DTO conversions
    public MaterialBuyDto getMaterialBuyDto(MaterialBuy entity) {
        return new MaterialBuyDto(
                entity.getPaymentId(),
                entity.getMaterialId(),
                entity.getSupplierId(),
                entity.getDate(),
                entity.getUnitAmount(),
                entity.getQuantity(),
                entity.getTotalPrice()
        );
    }

    public MaterialBuy getMaterialBuy(MaterialBuyDto dto) {
        return new MaterialBuy(
                dto.getPaymentId(),
                dto.getMaterialId(),
                dto.getSupplierId(),
                dto.getDate(),
                dto.getUnitAmount(),
                dto.getQuantity(),
                dto.getTotalPrice()
        );
    }
}