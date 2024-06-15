package lk.ijse.finalProject.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import lk.ijse.finalProject.repository.DriverRepo;
import lk.ijse.finalProject.repository.VehicleRepo;
import lk.ijse.finalProject.util.Regex;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class DriverAddFormController implements Initializable {
    public AnchorPane rootNode;
    public TextField txtFirstName;
    public TextField txtLastName;
    public TextField txtDob;
    public TextField txtNic;
    public TextField txtPhone;
    public TextField txtEmail;
    public TextField txtAddress;
    public Circle driverProfile;
    public Pane oldPane;
    public JFXComboBox<String > comboId;
    public Pane newPane;
    public String rest;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCombo();
    }

    private void setCombo() {
        ObservableList<String > obList = FXCollections.observableArrayList();
        try {
            List<String> allVehicleId = VehicleRepo.getAllVehicleId();
            for(String id : allVehicleId){
                obList.add(id);
                comboId.setItems(obList);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }
    public void btnSaveOnAction(ActionEvent actionEvent) {
            String firstName = txtFirstName.getText();
            String lastName = txtLastName.getText();
            String address = txtAddress.getText();
            Date dob = Date.valueOf(txtDob.getText());
            String nic = txtNic.getText();
            String phone = txtPhone.getText();
            String email = txtEmail.getText();
            String id = comboId.getValue();
            try {
                if (isValided()) {
                    String currentDriverId = DriverRepo.getCurrentDriverId();
                    String driverId = DriverRepo.generateDriverId(currentDriverId);
                    boolean isSaved = DriverRepo.saveDriver(driverId, firstName, lastName, address, nic, dob, id, phone, email, rest);
                    if (isSaved) {
                        clearFields();
                        setCombo();
                        newPane.setVisible(true);
                        oldPane.setOpacity(0.32);
                        new Alert(Alert.AlertType.CONFIRMATION, "Driver saved SuccessFully").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Driver can't save").show();
                    }
                }

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
    }

    private boolean isValided() {
        if (!Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.WORD,txtFirstName)) return false;
        if (!Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.WORD,txtLastName)) return false;
        if (!Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.WORD,txtAddress)) return false;
        if (!Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.DATE,txtDob)) return false;
        if (!Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.EMAIL,txtEmail)) return false;
        if (!Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.PHONE,txtPhone)) return false;
        if (!Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.NIC,txtNic)) return false;
        return true;
    }

    private void clearFields() {
        txtFirstName.clear();
        txtLastName.clear();
        txtAddress.clear();
        txtDob.clear();
        txtNic.clear();
        txtPhone.clear();
        txtEmail.clear();
        driverProfile.setFill(null);
    }

    public void txtFirstNameOnAction(ActionEvent actionEvent) {
        txtLastName.requestFocus();
    }

    public void txtLastNameOnAction(ActionEvent actionEvent) {
        txtAddress.requestFocus();
    }

    public void txtDobOnAction(ActionEvent actionEvent) {
        txtNic.requestFocus();
    }

    public void txtPhoneOnAction(ActionEvent actionEvent) {
        txtEmail.requestFocus();
    }

    public void txtEmailOnAction(ActionEvent actionEvent) {

    }

    public void txtAddressOnAction(ActionEvent actionEvent) {
        txtDob.requestFocus();
    }

    public void txtNicOnACtion(ActionEvent actionEvent) {
        txtPhone.requestFocus();
    }

    public void comboIdOnAction(ActionEvent actionEvent) {
//        newPane.setVisible(false);
//        oldPane.setVisible(true);
//        oldPane.setOpacity(1.0);
    }

    public void btnConformOnAction(ActionEvent actionEvent) {
        if (comboId.getValue() != null) {
            newPane.setVisible(false);
            oldPane.setVisible(true);
            oldPane.setOpacity(1.0);
        } else {
            new Alert(Alert.AlertType.INFORMATION,"Please choose available vehicle").show();
        }
    }

    public void btnCancelOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/view/tipsForm.fxml"));
        this.rootNode.getChildren().clear();
        this.rootNode.getChildren().add(pane);
    }

    public void btnAddProfilePhotoOnAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open My Files");
        fileChooser.setInitialDirectory(new File("/home/dhanujaya/Desktop/FinalProject/Final-Project/src/main/resources"));
        File selectedFile = fileChooser.showOpenDialog(null);
        String absolutePath = selectedFile.getAbsolutePath();
        String[] split =absolutePath.split("/home/dhanujaya/Desktop/FinalProject/Final-Project/src/main/resources");
        rest = split[1];
        Image image = new Image(String.valueOf(this.getClass().getResource(rest)));
        driverProfile.setFill(new ImagePattern(image));


    }

    public void firstNameKeyReleaseOnAction(KeyEvent keyEvent) {
        Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.WORD,txtFirstName);
    }

    public void lastNameKeyReleasedOnAction(KeyEvent keyEvent) {
        Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.WORD,txtLastName);
    }

    public void dobKeyReleasedOnAction(KeyEvent keyEvent) {
        Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.DATE,txtDob);
    }

    public void nicKeyReleasedOnAction(KeyEvent keyEvent) {
        Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.NIC,txtNic);
    }

    public void phoneKeyReleasedOnAction(KeyEvent keyEvent) {
        Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.PHONE,txtPhone);
    }

    public void emailKeyReleasedOnAction(KeyEvent keyEvent) {
        Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.EMAIL,txtEmail);
    }

    public void addressKeyReleasedOnAction(KeyEvent keyEvent) {
        Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.WORD,txtAddress);
    }
}
