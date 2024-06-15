package lk.ijse.finalProject.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import lk.ijse.finalProject.model.Driver;
import lk.ijse.finalProject.repository.DriverRepo;
import lk.ijse.finalProject.util.Regex;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class DriverUpdateFormController implements Initializable {
    public TextField txtFirstName;
    public TextField txtLastName;
    public TextField txtDob;
    public TextField txtNic;
    public TextField txtPhone;
    public TextField txtEmail;
    public TextField txtAddress;
    public Circle driverProfile;
    public JFXComboBox<String> comboDriverId;
    public String absolutePath;
    public AnchorPane rootNode;
    public Pane oldPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCombo();
    }

    private void setCombo() {
        ObservableList<String > obList = FXCollections.observableArrayList();
        try {
            List<String> idList = DriverRepo.setComboBox();
            for (String id : idList){
                obList.add(id);
            }
            comboDriverId.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void btnCancelOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane node = FXMLLoader.load(this.getClass().getResource("/view/driverTipsForm.fxml"));
        this.rootNode.getChildren().clear();
        this.rootNode.getChildren().add(node);
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = comboDriverId.getValue();
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String address = txtAddress.getText();
        String dob = txtDob.getText();
        String nic = txtNic.getText();
        String tel = txtPhone.getText();
        String email = txtEmail.getText();
        Driver driver = new Driver(id,firstName,lastName,address,dob,nic,tel,email,absolutePath);
        try {
            if (isVlided()) {
                boolean isUpdated = DriverRepo.updateDriver(driver);
                if (isUpdated) {
                    clearFields();
                    new Alert(Alert.AlertType.CONFIRMATION, "Driver Updated Successfully").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Driver can't Update").show();
                }
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    private boolean isVlided() {
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

    public void txtNicOnACtion(ActionEvent actionEvent) {
        txtPhone.requestFocus();
    }

    public void txtPhoneOnAction(ActionEvent actionEvent) {
        txtEmail.requestFocus();
    }

    public void txtEmailOnAction(ActionEvent actionEvent) {

    }

    public void txtAddressOnAction(ActionEvent actionEvent) {
        txtDob.requestFocus();
    }

    public void btnAddProfilePhotoOnAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open My Files");
        fileChooser.setInitialDirectory(new File("/home/dhanujaya/Desktop/FinalProject/Final-Project/src/main/resources"));
        File selectedFile = fileChooser.showOpenDialog(null);
        absolutePath = selectedFile.getAbsolutePath();
        String[] split =absolutePath.split("/home/dhanujaya/Desktop/FinalProject/Final-Project/src/main/resources");
        String rest = split[1];
        Image image = new Image(String.valueOf(this.getClass().getResource(rest)));
        driverProfile.setFill(new ImagePattern(image));
    }

    public void comboDriverIdOnAction(ActionEvent actionEvent) {
        String comboValue = comboDriverId.getValue();
        try {
            Driver driver = DriverRepo.getDriver(comboValue);
            txtFirstName.setText(driver.getFirstName());
            txtLastName.setText(driver.getLastname());
            txtAddress.setText(driver.getAddress());
            txtDob.setText(driver.getDob());
            txtNic.setText(driver.getNic());
            txtPhone.setText(driver.getContact());
            txtEmail.setText(driver.getEmail());
            driverProfile.setFill(null);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
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
