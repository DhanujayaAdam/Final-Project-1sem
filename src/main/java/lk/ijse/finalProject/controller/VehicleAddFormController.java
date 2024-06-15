package lk.ijse.finalProject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import lk.ijse.finalProject.model.Vehicle;
import lk.ijse.finalProject.model.VehicleSave;
import lk.ijse.finalProject.model.VehicleToBeService;
import lk.ijse.finalProject.repository.ServiseScheduleRepo;
import lk.ijse.finalProject.repository.VehicleRepo;
import lk.ijse.finalProject.repository.VehicleSaveRepo;
import lk.ijse.finalProject.util.Regex;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class VehicleAddFormController {
    public TextField txtModel;
    public TextField txtVehicleNumber;
    public TextField txtColor;
    public TextField txtYom;
    public TextField txtRegDate;
    public TextField txtCurrentMillage;
    public TextField txtChassisNumber;
    public TextField txtEngineNumber;
    public Circle vehicleProfile;
    public AnchorPane rootNode;
    public String rest;
    public String absolutePath;
    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void clearFields() {
        txtModel.clear();
        txtVehicleNumber.clear();
        txtYom.clear();
        txtColor.clear();
        txtEngineNumber.clear();
        txtChassisNumber.clear();
        txtRegDate.clear();
        txtCurrentMillage.clear();
        vehicleProfile.setFill(null);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String model = txtModel.getText();
        String vehicleNumber = txtVehicleNumber.getText();
        String chassis = txtChassisNumber.getText();
        String engineNumber = txtEngineNumber.getText();
        String color = txtColor.getText();
        String yom = txtYom.getText();
        Date date = Date.valueOf(txtRegDate.getText());
        double distance = Double.parseDouble(txtCurrentMillage.getText());
        try {
            String currentId = VehicleRepo.getVehicleId();
            String availableId = VehicleRepo.getVehicleId(currentId);
            Vehicle vehicle = new Vehicle(
                    availableId,
                    model,
                    vehicleNumber,
                    chassis,
                    engineNumber,
                    color,
                    yom,
                    date,
                    distance,
                    absolutePath
            );
            String vehicleServiceId = ServiseScheduleRepo.getShchedule1("Vehicle Service");
            String tyreReplacementId = ServiseScheduleRepo.getShchedule1("Tyre Replacement");
            VehicleToBeService vehicleService = new VehicleToBeService(vehicleServiceId,availableId,"Vehicle Service",0.0);
            VehicleToBeService tyreReplacement = new VehicleToBeService(tyreReplacementId,availableId,"Tyre Replacement",0.0);
            VehicleSave vs = new VehicleSave(vehicle,vehicleService,tyreReplacement);
            if (isValided()) {
                boolean isSaved = VehicleSaveRepo.saveVehicle(vs);
                if (isSaved) {
                    clearFields();
                    new Alert(Alert.AlertType.CONFIRMATION, "Vehicle saved successfully").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Vehicle can't save").show();
                }
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private boolean isValided() {
        if (!Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.WORD,txtModel)) return false;
        if (!Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.NUMBERPLATE,txtVehicleNumber)) return false;
        if (!Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.MIX,txtChassisNumber)) return false;
        if (!Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.DATE,txtRegDate)) return false;
        if (!Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.MIX,txtEngineNumber)) return false;
        if (!Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.YOM,txtYom)) return false;
        if (!Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.WORD,txtColor)) return false;
        if (!Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.DISTANCE,txtCurrentMillage)) return false;
        return true;
    }

    public void txtModelOnAction(ActionEvent actionEvent) {
        txtVehicleNumber.requestFocus();
    }

    public void txtVehicleNumberOnAction(ActionEvent actionEvent) {
        txtChassisNumber.requestFocus();
    }

    public void txtColorOnAction(ActionEvent actionEvent) {
        txtYom.requestFocus();
    }

    public void txtYomOnAction(ActionEvent actionEvent) {
        txtRegDate.requestFocus();
    }

    public void txtRegOnAction(ActionEvent actionEvent) {
        txtCurrentMillage.requestFocus();
    }

    public void txtCurrentDistanceOnAction(ActionEvent actionEvent) {
    }

    public void btnCancelOnAction(ActionEvent actionEvent) throws IOException {
        this.rootNode.getChildren().clear();
        this.rootNode.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/tipsForm.fxml")));
    }

    public void txtChassisOnAction(ActionEvent actionEvent) {
        txtEngineNumber.requestFocus();
    }

    public void txtEngineNumberOnaction(ActionEvent actionEvent) {
        txtColor.requestFocus();
    }

    public void btnChooseImageOnAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open My Files");
        fileChooser.setInitialDirectory(new File("/home/dhanujaya/Desktop/FinalProject/Final-Project/src/main/resources"));
        File selectedFile = fileChooser.showOpenDialog(null);
        absolutePath = selectedFile.getAbsolutePath();
        String[] split =absolutePath.split("/home/dhanujaya/Desktop/FinalProject/Final-Project/src/main/resources");
        rest = split[1];
        Image image = new Image(String.valueOf(this.getClass().getResource(rest)));
        vehicleProfile.setFill(new ImagePattern(image));
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

    }

    public void modelKeyReleasedOnAction(KeyEvent keyEvent) {
        Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.WORD,txtModel);
    }

    public void vehicleNumberKeyReleasedOnAction(KeyEvent keyEvent) {
        Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.NUMBERPLATE,txtVehicleNumber);
    }

    public void colorKeyReleasedOnAction(KeyEvent keyEvent) {
        Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.WORD,txtColor);
    }

    public void yomKeyReleasedOnAction(KeyEvent keyEvent) {
        Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.YOM,txtYom);
    }

    public void dateKeyReleasedOnAction(KeyEvent keyEvent) {
        Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.DATE,txtRegDate);
    }

    public void distanceKeyReleasedOnAction(KeyEvent keyEvent) {
        Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.DISTANCE,txtCurrentMillage);
    }

    public void chassisNumberKeyReleasedOnAction(KeyEvent keyEvent) {
        Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.MIX,txtChassisNumber);
    }

    public void engineNumberKeyReleasedOnAction(KeyEvent keyEvent) {
        Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.MIX,txtEngineNumber);
    }
}
