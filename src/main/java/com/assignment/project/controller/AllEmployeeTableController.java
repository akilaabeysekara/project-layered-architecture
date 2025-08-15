package com.assignment.project.controller;

import com.assignment.project.bo.BOFactory;
import com.assignment.project.bo.custom.EmployeeBO;
import com.assignment.project.dto.EmployeeDto;
import com.assignment.project.dto.tm.EmployeeTM;
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

public class AllEmployeeTableController {

    @FXML
    private JFXButton btnCloseTable;

    @FXML
    private TableColumn<EmployeeTM, String> colEmployeeAddress;

    @FXML
    private TableColumn<EmployeeTM, String> colEmployeeId;

    @FXML
    private TableColumn<EmployeeTM, String> colEmployeeName;

    @FXML
    private TableColumn<EmployeeTM, String> colEmployeePhone;

    @FXML
    private TableColumn<EmployeeTM, String> colEmployeeRole;

    @FXML
    private AnchorPane employeeTablePane;

    @FXML
    private TableView<EmployeeDto> tblEmployee;

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getInstance().getBO(BOFactory.BOType.EMPLOYEE);

    public void initialize(){
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmployeePhone.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        colEmployeeAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmployeeRole.setCellValueFactory(new PropertyValueFactory<>("role"));

        loadEmployeeData();
    }

    private void loadEmployeeData(){
        try {
            List<EmployeeDto> employees = employeeBO.getAllEmployee();
            ObservableList<EmployeeDto> employeeList = FXCollections.observableArrayList(employees);
            tblEmployee.setItems(employeeList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onEmployeeTableClose(ActionEvent event) {
        Stage stage = (Stage) btnCloseTable.getScene().getWindow();
        stage.close();
    }
}
