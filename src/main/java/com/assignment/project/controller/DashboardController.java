package com.assignment.project.controller;

import com.assignment.project.dao.custom.ClientDAO;
import com.assignment.project.dao.custom.DashboardDAO;
import com.assignment.project.view.tdm.ProjectExpensesTM;
import com.assignment.project.dao.custom.impl.ClientDAOImpl;
import com.assignment.project.dao.custom.impl.DashboardDAOImpl;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    DashboardDAO dashboardDAO = new DashboardDAOImpl();
    ClientDAO clientDAO = new ClientDAOImpl();

    @FXML
    private AnchorPane dashboardForm;

    @FXML
    private JFXListView<String> listAllProject;

    @FXML
    private Label label;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblClientCount;

    @FXML
    private Label lblTotalIncome;

    @FXML
    private Label lblTotalExpenses;

    @FXML
    private Label lblTotalProfit;

    @FXML
    private TableColumn<ProjectExpensesTM, String> colProjectID;

    @FXML
    private TableColumn<ProjectExpensesTM, String> colProjectName;

    @FXML
    private TableColumn<ProjectExpensesTM, Double> colProjectTotalCost;

    @FXML
    private TableColumn<ProjectExpensesTM, Double> colTotalExpenses;

    @FXML
    private TableColumn<ProjectExpensesTM, Double> colTotalMaterialCost;

    @FXML
    private TableView<ProjectExpensesTM> projectExpensesTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadProjectName();
        try {
            loadTotalIncome();
            loadTotalExpenses();
            loadTotalProfit();
            loadClientCount();
            loadProjectData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        lblDate.setText(LocalDate.now().toString());

    }

    private void loadProjectName(){
        try {
            ArrayList<String> projectName = dashboardDAO.getAllProjectName();
            ObservableList<String> observableList = FXCollections.observableArrayList();
            observableList.addAll(projectName);
            listAllProject.setItems(observableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadClientCount() throws SQLException {
        int count = clientDAO.getClientCount();
        lblClientCount.setText(String.valueOf(count));
    }

    private void loadTotalIncome() throws SQLException {
        double totalCash = dashboardDAO.getTotalIncome();
        lblTotalIncome.setText(String.valueOf(totalCash));
    }

    private void loadTotalExpenses() throws SQLException {
        double totalExpenses = dashboardDAO.getAllExpenses();
        lblTotalExpenses.setText(String.valueOf(totalExpenses));
    }

    private void loadTotalProfit() throws SQLException {
        double totalCash = dashboardDAO.getTotalIncome();
        double totalExpenses = dashboardDAO.getAllExpenses();
        lblTotalProfit.setText(String.valueOf(totalCash - totalExpenses));
    }

    private void loadProjectData() throws SQLException {
        ObservableList<ProjectExpensesTM> projectList = FXCollections.observableArrayList(dashboardDAO.getProjectDetails());

        colProjectID.setCellValueFactory(new PropertyValueFactory<>("projectID"));
        colProjectName.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        colTotalExpenses.setCellValueFactory(new PropertyValueFactory<>("totalExpenses"));
        colTotalMaterialCost.setCellValueFactory(new PropertyValueFactory<>("totalMaterialCost"));
        colProjectTotalCost.setCellValueFactory(new PropertyValueFactory<>("projectTotalCost"));

        projectExpensesTable.setItems(projectList);
    }

}
