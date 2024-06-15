package lk.ijse.finalProject.controller;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.SwipeEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import lk.ijse.finalProject.DB.Dbconnection;
import lk.ijse.finalProject.model.Vehicle;
import lk.ijse.finalProject.model.tm.VehicleTm;
import lk.ijse.finalProject.repository.DriverRepo;
import lk.ijse.finalProject.repository.VehicleRepo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static lk.ijse.finalProject.repository.VehicleRepo.getVehicleId;

public class VehicleFormController {
    public AnchorPane rootNode;
    public Circle vp1;
    public Circle vp2;
    public Circle vp3;
    public Circle vp4;
    public Circle vp5;
    public Circle vp6;
    public Circle vp7;
    public Circle profilePicture;
    public Pane node;
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    public JFXButton btnAlert;
    public JFXButton btnTips;
    public Hyperlink hplVehicle1;
    public Label lblNumber1;
    public Hyperlink hplDriver2;
    public Label lblNumber2;
    public Hyperlink hplVehicle3;
    public Label lblNumber3;
    public Hyperlink hplVehicle4;
    public Label lblNumber4;
    public Hyperlink hplVehicle5;
    public Label lblNumber5;
    public Hyperlink hplVehicle6;
    public Label lblNumber6;
    public Hyperlink hplVehicle7;
    public Label lblNumber7;
    public JFXButton txtTips;
    public Label userName;
    public TextField txtSearchBar;
    public Label lblDatePicker;

    public void initialize(){
        setProfile();
        setNumberPlate();
        setName();
    }

    private void setName() {
        try {
            List<String> vehicleName = VehicleRepo.getVehicleName();
            if(vehicleName.size() < 1){
                hplVehicle1.setText("no data");
            } else {
                hplVehicle1.setText(vehicleName.get(0));
            }
            if (vehicleName.size() < 2) {
                hplDriver2.setText("no data");
            } else {
                hplDriver2.setText(vehicleName.get(1));
            }
            if (vehicleName.size() < 3) {
                hplVehicle3.setText("no data");
            } else {
                hplVehicle3.setText(vehicleName.get(2));
            }
            if (vehicleName.size() < 4) {
                hplVehicle4.setText("no data");
            } else {
                hplVehicle4.setText(vehicleName.get(3));
            }
            if (vehicleName.size() < 5) {
                hplVehicle5.setText("no data");
            } else {
                hplVehicle5.setText(vehicleName.get(4));
            }
            if (vehicleName.size() < 6) {
                hplVehicle6.setText("no data");
            } else {
                hplVehicle6.setText(vehicleName.get(5));
            }
            if (vehicleName.size() < 7){
                hplVehicle7.setText("no data");
            } else {
                hplVehicle7.setText(vehicleName.get(6));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void setNumberPlate() {
        try {
            List<String> vehicleNumber = VehicleRepo.getVehicleNumber();
            if(vehicleNumber.size() < 1){
                lblNumber1.setText("no data");
            } else {
                lblNumber1.setText(vehicleNumber.get(0));
            }
            if (vehicleNumber.size() < 2) {
                lblNumber2.setText("no data");
            } else {
                lblNumber2.setText(vehicleNumber.get(1));
            }
            if (vehicleNumber.size() < 3) {
                lblNumber3.setText("no data");
            } else {
                lblNumber3.setText(vehicleNumber.get(2));
            }
            if (vehicleNumber.size() < 4) {
                lblNumber4.setText("no data");
            } else {
                lblNumber4.setText(vehicleNumber.get(3));
            }
            if (vehicleNumber.size() < 5) {
                lblNumber5.setText("no data");
            } else {
                lblNumber5.setText(vehicleNumber.get(4));
            }
            if (vehicleNumber.size() < 6) {
                lblNumber6.setText("no data");
            } else {
                lblNumber6.setText(vehicleNumber.get(5));
            }
            if (vehicleNumber.size() < 7){
                lblNumber7.setText("no data");
            } else {
                lblNumber7.setText(vehicleNumber.get(6));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void setProfile() {
        Image image = new Image(String.valueOf(this.getClass().getResource("/image/humen1.jpeg")));
        profilePicture.setFill(new ImagePattern(image));

        Image image1 = new Image(String.valueOf(this.getClass().getResource("/truck/daf.jpeg")));
        Image image2 = new Image(String.valueOf(this.getClass().getResource("/truck/Scania3.jpg")));
        Image image3 = new Image(String.valueOf(this.getClass().getResource("/truck/image-2.jpeg")));
        Image image4 = new Image(String.valueOf(this.getClass().getResource("/truck/Scania1.jpeg")));
        Image image5 = new Image(String.valueOf(this.getClass().getResource("/truck/DAF1.jpeg")));
        Image image6 = new Image(String.valueOf(this.getClass().getResource("/truck/Scania2.jpg")));
        Image image7 = new Image(String.valueOf(this.getClass().getResource("/truck/daf.jpg")));

        vp1.setFill(new ImagePattern(image1));
        vp2.setFill(new ImagePattern(image2));
        vp3.setFill(new ImagePattern(image3));
        vp4.setFill(new ImagePattern(image4));
        vp5.setFill(new ImagePattern(image5));
        vp6.setFill(new ImagePattern(image6));
        vp7.setFill(new ImagePattern(image7));
    }
    public void btnTipsOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/view/tipsForm.fxml"));
        this.node.getChildren().clear();
        this.node.getChildren().add(pane);
        btnTips.setCursor(Cursor.HAND);

    }

    public void btnVehicleAddOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/view/vehicleAddForm.fxml"));
        this.node.getChildren().clear();
        this.node.getChildren().add(pane);

    }

    public void btnVehicleUpdateOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/view/vehicleUpdateForm.fxml"));
        this.node.getChildren().clear();;
        this.node.getChildren().add(pane);

    }

    public void btnAlertOnAction(ActionEvent actionEvent) throws IOException, JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/report/VehicleDetail.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String,Object> data = new HashMap<>();
        data.put("Date" ,lblDatePicker.getText());
        System.out.println("Invoke this");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null,Dbconnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint);
    }

    public void hpl1OnAction(ActionEvent actionEvent) throws IOException {
        String vehicleNumber = lblNumber1.getText();
        try {
            Vehicle vehicle = VehicleRepo.getDetail(vehicleNumber);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/vehicleViewForm.fxml"));
            loader.load();
            VehicleViewFormController vehicleView = loader.getController();
            vehicleView.sendName(vehicle);
            BorderPane pane = loader.getRoot();
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setTitle("Vehicle View");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void hpl2OnAction(ActionEvent actionEvent) {
        String vehicleNumber = lblNumber2.getText();
        try {
            Vehicle vehicle = VehicleRepo.getDetail(vehicleNumber);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/vehicleViewForm.fxml"));
            loader.load();
            VehicleViewFormController vehicleView = loader.getController();
            vehicleView.sendName(vehicle);
            BorderPane pane = loader.getRoot();
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setTitle("Vehicle View");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void hpl3OnAction(ActionEvent actionEvent) {
        String vehicleNumber = lblNumber3.getText();
        try {
            Vehicle vehicle = VehicleRepo.getDetail(vehicleNumber);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/vehicleViewForm.fxml"));
            loader.load();
            VehicleViewFormController vehicleView = loader.getController();
            vehicleView.sendName(vehicle);
            BorderPane pane = loader.getRoot();
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setTitle("Vehicle View");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (SQLException | IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void hpl4OnAction(ActionEvent actionEvent) {
        String vehicleNumber = lblNumber4.getText();
        try {
            Vehicle vehicle = VehicleRepo.getDetail(vehicleNumber);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/vehicleViewForm.fxml"));
            loader.load();
            VehicleViewFormController vehicleView = loader.getController();
            vehicleView.sendName(vehicle);
            BorderPane pane = loader.getRoot();
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setTitle("Vehicle View");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (SQLException | IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void hpl5OnAction(ActionEvent actionEvent) {
        String vehicleNumber = lblNumber5.getText();
        try {
            Vehicle vehicle = VehicleRepo.getDetail(vehicleNumber);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/vehicleViewForm.fxml"));
            loader.load();
            VehicleViewFormController vehicleView = loader.getController();
            vehicleView.sendName(vehicle);
            BorderPane pane = loader.getRoot();
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setTitle("Vehicle View");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (SQLException | IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void hpl6OnAction(ActionEvent actionEvent) {
        String vehicleNumber = lblNumber6.getText();
        try {
            Vehicle vehicle = VehicleRepo.getDetail(vehicleNumber);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/vehicleViewForm.fxml"));
            loader.load();
            VehicleViewFormController vehicleView = loader.getController();
            vehicleView.sendName(vehicle);
            BorderPane pane = loader.getRoot();
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setTitle("Vehicle View");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (SQLException | IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void hpl7OnAction(ActionEvent actionEvent) {
        String vehicleNumber = lblNumber7.getText();
        try {
            Vehicle vehicle = VehicleRepo.getDetail(vehicleNumber);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/vehicleViewForm.fxml"));
            loader.load();
            VehicleViewFormController vehicleView = loader.getController();
            vehicleView.sendName(vehicle);
            BorderPane pane = loader.getRoot();
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setTitle("Vehicle View");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (SQLException | IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }



    public void hplViewDriverOnAction(ActionEvent actionEvent) {
    }

    public void hplVehicle1OnAction(ActionEvent actionEvent) {
        String vehicleNumber = lblNumber1.getText();
        try {
            Vehicle vehicle = VehicleRepo.getDetail(vehicleNumber);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/vehicleViewForm.fxml"));
            loader.load();
            VehicleViewFormController vehicleView = loader.getController();
            vehicleView.sendName(vehicle);
            BorderPane pane = loader.getRoot();
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setTitle("Vehicle View");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (SQLException | IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    public void hplDriver2OnAction(ActionEvent actionEvent) {
        try {

            Vehicle vehicle = VehicleRepo.getDetail(String.valueOf(lblNumber2));
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/vehicleViewForm.fxml"));
            loader.load();
            VehicleViewFormController vehicleView = loader.getController();
            vehicleView.sendName(vehicle);
            BorderPane pane = loader.getRoot();
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setTitle("Vehicle View");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }
    }

    public void hplVehicle3OnAction(ActionEvent actionEvent) {
    }

    public void hplVehicle4OnAction(ActionEvent actionEvent) {
    }

    public void hplVehicle5OnAction(ActionEvent actionEvent) {
    }

    public void hplVehicle6OnAction(ActionEvent actionEvent) {
    }

    public void hplVehicle7OnAction(ActionEvent actionEvent) {
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
    }
}
