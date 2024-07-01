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
import lk.ijse.finalProject.bo.custom.ServiceSheduleBO;
import lk.ijse.finalProject.bo.custom.VehicleBO;
import lk.ijse.finalProject.bo.custom.VehicleToBeServicedBO;
import lk.ijse.finalProject.bo.custom.impl.ServiceScheduleBOImpl;
import lk.ijse.finalProject.bo.custom.impl.VehicleBOImpl;
import lk.ijse.finalProject.bo.custom.impl.VehicleTOBeServicedBOImpl;
import lk.ijse.finalProject.db.Dbconnection;
import lk.ijse.finalProject.dto.VehicleDTO;
import lk.ijse.finalProject.dto.VehicleToBeServiceDTO;
import lk.ijse.finalProject.util.Regex;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;

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
    VehicleBO vehicleBO = new VehicleBOImpl();
    ServiceSheduleBO serviceSheduleBO = new ServiceScheduleBOImpl();
    VehicleToBeServicedBO vehicleToBeServicedBO = new VehicleTOBeServicedBOImpl();
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
        if(isValided()) {
            boolean isSaved = saveVehicle();
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Vehicle Saved Successfully").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Vehicle Saved Unsuccessfully").show();
            }
        }
    }
    public boolean saveVehicle() {
        String model = txtModel.getText();
        String vehicleNumber = txtVehicleNumber.getText();
        String chassis = txtChassisNumber.getText();
        String engineNumber = txtEngineNumber.getText();
        String color = txtColor.getText();
        String yom = txtYom.getText();
        Date date = Date.valueOf(txtRegDate.getText());
        double distance = Double.parseDouble(txtCurrentMillage.getText());

        try {
            String currentId = vehicleBO.getVehicleId();
            String availableId = null;
            if (currentId != null) {
                String[] split = currentId.split("V");
                int idNum = Integer.parseInt(split[1]);
                availableId = "V" + ++idNum;
            } else {
                availableId = "V1";
            }

            String vehicleServiceId = serviceSheduleBO.getShchedule("Vehicle Service");
            String tyreReplacementId = serviceSheduleBO.getShchedule("Tyre Replacement");
            VehicleToBeServiceDTO vehicleService = new VehicleToBeServiceDTO(vehicleServiceId, availableId, "Vehicle Service", 0.0);
            VehicleToBeServiceDTO tyreReplacement = new VehicleToBeServiceDTO(tyreReplacementId, availableId, "Tyre Replacement", 0.0);
            System.out.println("come to try catch");

            //vehicle save transaction
            Connection connection = Dbconnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean isRegistered = vehicleBO.saveVehicle(new VehicleDTO(availableId, model, vehicleNumber, chassis, engineNumber, color, yom, date, distance, absolutePath));
            if (!isRegistered) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
            System.out.println("invoke state1");
            boolean isSaved = vehicleToBeServicedBO.addReport(vehicleService);
            boolean isAllCorrect = vehicleToBeServicedBO.addReport(tyreReplacement);
            if (!isSaved && isAllCorrect) {
                System.out.println("invoke state2");
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        return false;
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
        fileChooser.setInitialDirectory(new File("/home/dhanujaya/Desktop/Final-Project/src/main/resources"));
        File selectedFile = fileChooser.showOpenDialog(null);
        absolutePath = selectedFile.getAbsolutePath();
        String[] split =absolutePath.split("/home/dhanujaya/Desktop/Final-Project/src/main/resources");
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
