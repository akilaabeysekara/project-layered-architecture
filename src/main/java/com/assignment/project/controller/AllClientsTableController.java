package com.assignment.project.controller;

import com.assignment.project.bo.BOFactory;
import com.assignment.project.bo.BOTypes;
import com.assignment.project.bo.custom.ClientBO;
import com.assignment.project.dto.ClientDto;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class AllClientsTableController {
    ClientBO clientBO = (ClientBO) BOFactory.getInstance().getBO(BOTypes.CLIENT);

    @FXML
    private AnchorPane clientTablePane;

    @FXML
    private JFXButton btnCloseTable;

    @FXML
    private TableView<ClientDto> tblClients;

    @FXML
    private TableColumn<ClientDto, String> colClientId;

    @FXML
    private TableColumn<ClientDto, String> colClientName;

    @FXML
    private TableColumn<ClientDto, String> colClientAddress;

    @FXML
    private TableColumn<ClientDto, String> colClientPhone;

    @FXML
    private TableColumn<ClientDto, String> colClientEmail;

    public void initialize() {
        colClientId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colClientName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colClientAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colClientPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        colClientEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        loadClientData();
    }

    private void loadClientData() {
        try {
            List<ClientDto> clients = clientBO.getAllClient();
            ObservableList<ClientDto> clientList = FXCollections.observableArrayList(clients);
            tblClients.setItems(clientList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onClientTableClose(ActionEvent event) {
        Stage stage = (Stage) btnCloseTable.getScene().getWindow();
        stage.close();
    }
}
