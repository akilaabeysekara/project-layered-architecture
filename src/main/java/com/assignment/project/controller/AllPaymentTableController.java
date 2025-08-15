package com.assignment.project.controller;

import com.assignment.project.bo.BOFactory;
import com.assignment.project.bo.custom.PaymentBO;
import com.assignment.project.dto.PaymentDto;
import com.assignment.project.dto.tm.PaymentTM;
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

public class AllPaymentTableController {

    @FXML
    private JFXButton btnCloseTable;

    @FXML
    private TableColumn<PaymentTM, String> colPaymentAmount;

    @FXML
    private TableColumn<PaymentTM, String> colPaymentDate;

    @FXML
    private TableColumn<PaymentTM, String> colPaymentId;

    @FXML
    private TableColumn<PaymentTM, String> colPaymentType;

    @FXML
    private TableColumn<PaymentTM, String> colProjectId;

    @FXML
    private AnchorPane paymentTablePane;

    @FXML
    private TableView<PaymentDto> tblPayment;

    PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOType.PAYMENT);

    public void initialize(){
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentID"));
        colProjectId.setCellValueFactory(new PropertyValueFactory<>("projectID"));
        colPaymentDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colPaymentType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colPaymentAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        loadPaymentData();
    }

    private void loadPaymentData(){
        try {
            List<PaymentDto> payments = paymentBO.getAllPayment();
            ObservableList<PaymentDto> paymentList = FXCollections.observableArrayList(payments);
            tblPayment.setItems(paymentList);
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