package lk.ijse.finalProject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import lk.ijse.finalProject.bo.BOFactory;
import lk.ijse.finalProject.bo.custom.DriverBO;
import lk.ijse.finalProject.bo.custom.impl.DriverBOImpl;
import lk.ijse.finalProject.dto.DriverDTO;
import lk.ijse.finalProject.dao.custom.impl.DriverDAOImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DriverViewFormController implements Initializable {
    public TextField txtFirstName;
    public TextField txtLastName;
    public TextField txtAddress;
    public TextField txtDob;
    public TextField txtNic;
    public TextField txtPhone;
    public TextField txtEmail;
    public BorderPane rootNode;
    public Circle employeeProfile;
    public TextField txtSearch;
    public JFXButton btnBack;
    DriverBO driverBO = (DriverBO) BOFactory.getBoFactory().getInstance(BOFactory.BoType.DRIVER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void btnBackOnAction(ActionEvent actionEvent) {


    }

    public void hplEditOnAction(ActionEvent actionEvent) {
    }

    public void hplDeleteOnAction(ActionEvent actionEvent) {
        String firstName = txtFirstName.getText();
        try {
            boolean isDeleted = driverBO.deleteDriver(firstName);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Driver deleted successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR,"Driver deleted unsuccessfully").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void sendDriver(DriverDTO driver) {
        txtFirstName.setText(driver.getFirstName());
        txtLastName.setText(driver.getLastname());
        txtAddress.setText(driver.getAddress());
        txtDob.setText(String.valueOf(driver.getDob()));
        txtNic.setText(driver.getNic());
        txtPhone.setText(driver.getContact());
        txtEmail.setText(driver.getEmail());
        employeeProfile.setFill(new ImagePattern(new Image(getClass().getResourceAsStream("/assets/blankProfile/images.png"))));
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String firstName = txtSearch.getText();
        try {
            DriverDTO driver = driverBO.getDriver(firstName);
            txtFirstName.setText(driver.getFirstName());
            txtLastName.setText(driver.getLastname());
            txtAddress.setText(driver.getAddress());
            txtDob.setText(String.valueOf(driver.getDob()));
            txtNic.setText(driver.getNic());
            txtPhone.setText(driver.getContact());
            txtEmail.setText(driver.getEmail());
            employeeProfile.setFill(null);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
}
