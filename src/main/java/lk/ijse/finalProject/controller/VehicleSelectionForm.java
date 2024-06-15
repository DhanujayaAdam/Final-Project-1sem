package lk.ijse.finalProject.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.finalProject.repository.DriverRepo;
import lk.ijse.finalProject.repository.VehicleRepo;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class VehicleSelectionForm implements Initializable {

    public JFXComboBox<String> comboId;
    public AnchorPane rootNode;

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

    public void btnConformOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/view/driverAddForm.fxml"));
        this.rootNode.getChildren().clear();
        this.rootNode.getChildren().add(pane);
    }

    public void btnCancelOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/driverAddForm.fxml"));
        this.rootNode.getChildren().clear();
        this.rootNode.getChildren().add(rootNode);
    }

    public void btnCancelOnAction1(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/driverAddForm.fxml"));
        this.rootNode.getChildren().clear();
        this.rootNode.getChildren().add(rootNode);

    }

    public void comboIdOnAction(ActionEvent actionEvent) {

    }
}

