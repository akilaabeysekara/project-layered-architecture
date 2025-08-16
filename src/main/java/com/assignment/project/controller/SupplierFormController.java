package com.assignment.project.controller;

import com.assignment.project.bo.BOFactory;
import com.assignment.project.bo.BOTypes;
import com.assignment.project.bo.custom.SupplierBO;
import com.assignment.project.dto.SupplierDto;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {
    private final SupplierBO supplierBO = (SupplierBO) BOFactory.getInstance().getBO(BOTypes.SUPPLIER);

    @FXML
    private JFXButton btnSupplierDelete;

    @FXML
    private JFXButton btnSupplierReset;

    @FXML
    private JFXButton btnSupplierSave;

    @FXML
    private JFXButton btnSupplierUpdate;

    @FXML
    private JFXComboBox<String> cmbSupplierId;

    @FXML
    private Label lblSupplierID;

    @FXML
    private AnchorPane supplierPane;

    @FXML
    private JFXTextField txtSupplierAddress;

    @FXML
    private JFXTextField txtSupplierEmail;

    @FXML
    private JFXTextField txtSupplierName;

    @FXML
    private JFXTextField txtSupplierPhoneNo;

    @FXML
    void cmbSupplierOnAction(ActionEvent event) throws SQLException {
        String selectedSupplierId = cmbSupplierId.getSelectionModel().getSelectedItem();
        if (selectedSupplierId != null) {
            SupplierDto supplierDto = supplierBO.findBySupplierId(selectedSupplierId);

            if (supplierDto != null) {
                lblSupplierID.setText(supplierDto.getSupplierId());
                txtSupplierName.setText(supplierDto.getName());
                txtSupplierAddress.setText(supplierDto.getAddress());
                txtSupplierPhoneNo.setText(supplierDto.getPhoneNo());
                txtSupplierEmail.setText(supplierDto.getEmail());

                btnSupplierUpdate.setDisable(false);
                btnSupplierDelete.setDisable(false);
                btnSupplierSave.setDisable(true);
            }
        }
    }

    @FXML
    void onDeleteSupplier(ActionEvent event) {
        try {
            String supplierId = lblSupplierID.getText();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this Supplier?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.isPresent() && buttonType.get() == ButtonType.YES) {
                boolean isDeleted = supplierBO.deleteSupplier(supplierId);

                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "Supplier deleted successfully!").show();
                    refreshPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete supplier.").show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error occurred while deleting supplier.").show();
        }
    }

    @FXML
    void onLoadMachineTable(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AllSupplierTable.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);

            Scene scene = new Scene(root);
            scene.setFill(null);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error loading supplier table.").show();
        }
    }

    @FXML
    void onSaveSupplier(ActionEvent event) {
        String supplierId = lblSupplierID.getText();
        String supplierName = txtSupplierName.getText();
        String supplierAddress = txtSupplierAddress.getText();
        String supplierPhoneNo = txtSupplierPhoneNo.getText();
        String supplierEmail = txtSupplierEmail.getText();

        String namePattern = "^[A-Za-z ]+$";
        String addressPattern = "^[A-Za-z0-9 ,.-]+$";
        String phonePattern = "^\\d{10}$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        boolean isValidName = supplierName.matches(namePattern);
        boolean isValidAddress = supplierAddress.matches(addressPattern);
        boolean isValidPhone = supplierPhoneNo.matches(phonePattern);
        boolean isValidEmail = supplierEmail.matches(emailPattern);

        resetFieldStyles();

        if (!isValidName) {
            txtSupplierName.setStyle("-fx-border-color: red;");
        }
        if (!isValidAddress) {
            txtSupplierAddress.setStyle("-fx-border-color: red;");
        }
        if (!isValidPhone) {
            txtSupplierPhoneNo.setStyle("-fx-border-color: red;");
        }
        if (!isValidEmail) {
            txtSupplierEmail.setStyle("-fx-border-color: red;");
        }

        if (isValidName && isValidAddress && isValidPhone && isValidEmail) {
            try {
                SupplierDto supplierDto = new SupplierDto(supplierId, supplierName, supplierAddress, supplierPhoneNo, supplierEmail);
                boolean isSaved = supplierBO.saveSupplier(supplierDto);

                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Supplier saved successfully!").show();
                    refreshPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save supplier!").show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Error saving supplier.").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Please fix the highlighted fields.").show();
        }
    }

    @FXML
    void onSupplierResetAction(ActionEvent event) {
        try {
            refreshPage();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error resetting form.").show();
        }
    }

    @FXML
    void onUpdateSupplier(ActionEvent event) {
        String supplierId = lblSupplierID.getText();
        String supplierName = txtSupplierName.getText();
        String supplierAddress = txtSupplierAddress.getText();
        String supplierPhoneNo = txtSupplierPhoneNo.getText();
        String supplierEmail = txtSupplierEmail.getText();

        String namePattern = "^[A-Za-z ]+$";
        String addressPattern = "^[A-Za-z0-9 ,.-]+$";
        String phonePattern = "^\\d{10}$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        boolean isValidName = supplierName.matches(namePattern);
        boolean isValidAddress = supplierAddress.matches(addressPattern);
        boolean isValidPhone = supplierPhoneNo.matches(phonePattern);
        boolean isValidEmail = supplierEmail.matches(emailPattern);

        resetFieldStyles();

        if (!isValidName) {
            txtSupplierName.setStyle("-fx-border-color: red;");
        }
        if (!isValidAddress) {
            txtSupplierAddress.setStyle("-fx-border-color: red;");
        }
        if (!isValidPhone) {
            txtSupplierPhoneNo.setStyle("-fx-border-color: red;");
        }
        if (!isValidEmail) {
            txtSupplierEmail.setStyle("-fx-border-color: red;");
        }

        if (isValidName && isValidAddress && isValidPhone && isValidEmail) {
            try {
                SupplierDto supplierDto = new SupplierDto(supplierId, supplierName, supplierAddress, supplierPhoneNo, supplierEmail);
                boolean isUpdated = supplierBO.updateSupplier(supplierDto);

                if (isUpdated) {
                    new Alert(Alert.AlertType.INFORMATION, "Supplier updated successfully!").show();
                    refreshPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to update supplier.").show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Error updating supplier.").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Please fix the highlighted fields.").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            refreshPage();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error initializing form.").show();
        }
    }

    private void refreshPage() throws SQLException {
        String nextSupplierId = supplierBO.getNextSupplierId();
        lblSupplierID.setText(nextSupplierId);

        loadSupplierIds();

        cmbSupplierId.getSelectionModel().clearSelection();
        txtSupplierName.clear();
        txtSupplierPhoneNo.clear();
        txtSupplierEmail.clear();
        txtSupplierAddress.clear();

        resetFieldStyles();

        btnSupplierSave.setDisable(false);
        btnSupplierUpdate.setDisable(true);
        btnSupplierDelete.setDisable(true);
    }

    private void resetFieldStyles() {
        txtSupplierName.setStyle("-fx-border-color: #7367F0;");
        txtSupplierAddress.setStyle("-fx-border-color: #7367F0;");
        txtSupplierPhoneNo.setStyle("-fx-border-color: #7367F0;");
        txtSupplierEmail.setStyle("-fx-border-color: #7367F0;");
    }

    private void loadSupplierIds() throws SQLException {
        ArrayList<String> supplierIds = supplierBO.getAllSupplierIds();
        ObservableList<String> observableList = FXCollections.observableArrayList(supplierIds);
        cmbSupplierId.setItems(observableList);
    }
}