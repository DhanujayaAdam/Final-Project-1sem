package lk.ijse.finalProject.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import lk.ijse.finalProject.bo.BOFactory;
import lk.ijse.finalProject.bo.custom.*;
import lk.ijse.finalProject.controller.mail.Mail;
import lk.ijse.finalProject.db.Dbconnection;
import lk.ijse.finalProject.dto.*;
import lk.ijse.finalProject.entity.*;
import view.tm.PackageTm;
import lk.ijse.finalProject.util.Regex;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class PackageFormController implements Initializable {
    public TableColumn<?,?> colmTrackingNumber;
    public TableColumn<?,?> columnCompany;
    public TableColumn<?,?> columnTypeOfGood;
    public TableColumn<?,?> columnWeight;
    public TableColumn<?,?> columnPrice;
    public TableColumn<?,?> columnDelete;
    public TextField txtWeight;
    public TableView<PackageTm> tblPackage;
    public JFXComboBox<String> comboId;
    public AnchorPane oldPane;
    public TextField txtDate;
    public TextField txtType;
    public JFXComboBox<String> comboRoute;
    public TextField txtLocation;
    public TextField txtDestination;
    public TextField txtDistance;
    public JFXComboBox<String> comboDescription;
    public Circle picture1;
    public Circle picture2;
    public Circle picture3;
    public Circle picture4;
    public Circle picture5;
    public Label packageType1;
    public Label packageType2;
    public Label packageType3;
    public Label packageType4;
    public Label packageType5;
    public PieChart pieChart;
    public Label userName;
    public Circle profilePicture;
    public TextField txtSearchBar;
    public Label lblDatePicker;
    public AnchorPane newPane;
    public JFXComboBox<String> comboVehicleId;
    public String user;
    public Label tracking1;
    public Label tracking2;
    public Label tracking3;
    public Label tracking4;
    public Label tracking5;
    VehicleBO vehicleBO = (VehicleBO) BOFactory.getBoFactory().getInstance(BOFactory.BoType.VEHICLE);
    PackageBO packageBO = (PackageBO) BOFactory.getBoFactory().getInstance(BOFactory.BoType.PACKAGE);
    ShipmentBO shipmentBO = (ShipmentBO) BOFactory.getBoFactory().getInstance(BOFactory.BoType.SHIPMENT);
    RouteBO routeBO = (RouteBO) BOFactory.getBoFactory().getInstance(BOFactory.BoType.ROUTE);
    DeliveryDetailBO deliveryDetailBO = (DeliveryDetailBO) BOFactory.getBoFactory().getInstance(BOFactory.BoType.DELIVERY_DETAIL);
    DriverBO driverBO = (DriverBO) BOFactory.getBoFactory().getInstance(BOFactory.BoType.DRIVER);
    VehicleToBeServicedBO vehicleToBeServicedBO = (VehicleToBeServicedBO) BOFactory.getBoFactory().getInstance(BOFactory.BoType.VEHICLE_TO_BE_SERVICED);
    ClientBO clientBO = (ClientBO) BOFactory.getBoFactory().getInstance(BOFactory.BoType.CLIENT);

    public void sendUser(String dbUser) {
        user = dbUser;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCombo();
        setComboVehicleId();
        setComboDescription();
        setDate();
        setComboRoute();
        setTable();
        setCellValueFactory();
        setUserName();
        setProfile();
        setpackageName();

    }

    private void setpackageName() {
        try {
            List<String> trackingList = packageBO.getPackageList();

            if (trackingList.size() < 1){
                tracking1.setText("No Data");
            } else {
                tracking1.setText(trackingList.get(0));
            }
            if (trackingList.size() < 2){
                tracking2.setText("No Data");
            } else {
                tracking2.setText(trackingList.get(1));
            }
            if (trackingList.size() < 3){
                tracking3.setText("No Data");
            } else {
                tracking3.setText(trackingList.get(2));
            }
            if (trackingList.size() < 4){
                tracking4.setText("No Data");
            } else {
                tracking4.setText(trackingList.get(3));
            }
            if (trackingList.size() < 5){
                tracking5.setText("No Data");
            } else {
                tracking5.setText(trackingList.get(4));
            }

            List<String> packageList = packageBO.getTypeOfGood();
            if (packageList.size() < 1){
                packageType1.setText("No Data");
            } else {
                packageType1.setText(packageList.get(0));
            }
            if (packageList.size() < 2){
                packageType2.setText("No Data");
            } else {
                packageType2.setText(packageList.get(1));
            }
            if (packageList.size() < 3){
                packageType3.setText("No Data");
            } else {
                packageType3.setText(packageList.get(2));
            }
            if (packageList.size() < 4){
                packageType4.setText("No Data");
            } else {
                packageType4.setText(packageList.get(3));
            }
            if (packageList.size() < 5){
                packageType5.setText("No Data");
            } else {
                packageType5.setText(packageList.get(4));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void setProfile() {
        Image image = new Image(getClass().getResourceAsStream("/assets/image/humen1.jpeg"));
        profilePicture.setFill(new ImagePattern(image));
        Image image1 = new Image(getClass().getResourceAsStream("/assets/package/HealthiestFruits-feb2318dc0a3454993007f57c724753f.jpg"));
        Image image2 = new Image(getClass().getResourceAsStream("/assets/package/fast-fashion2.jpeg"));
        Image image3 = new Image(getClass().getResourceAsStream("/assets/package/086924c9-23b7-41c6-9218-45685c563a2e-h.jpeg"));
        Image image4 = new Image(getClass().getResourceAsStream("/assets/package/images.jpeg"));
        Image image5 = new Image(getClass().getResourceAsStream("/assets/package/1_0x0_2119x1415_0x520_new_tyres.jpg"));
        picture1.setFill(new ImagePattern(image1));
        picture2.setFill(new ImagePattern(image2));
        picture3.setFill(new ImagePattern(image3));
        picture4.setFill(new ImagePattern(image4));
        picture5.setFill(new ImagePattern(image5));
    }

    private void setUserName() {
        userName.setText(user);
    }

    private void setCellValueFactory() {
        columnCompany.setCellValueFactory(new PropertyValueFactory<>("companyId"));
        colmTrackingNumber.setCellValueFactory(new PropertyValueFactory<>("trackingNumber"));
        columnWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        columnTypeOfGood.setCellValueFactory(new PropertyValueFactory<>("typeOfGood"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    private void setTable() {
        ObservableList<PackageTm> obList = FXCollections.observableArrayList();
        try {
            List<PackageDTO> packageList = packageBO.getAllPackage();
            for (PackageDTO pack : packageList){
                PackageTm tm = new PackageTm(
                        pack.getOrderId(),
                        pack.getTrackingNumber(),
                        pack.getCompanyId(),
                        pack.getTypeOfGood(),
                        pack.getWeight(),
                        pack.getDeliveryType(),
                        pack.getBorrowDAte(),
                        pack.getShipmentId(),
                        pack.getWeight()*500
                );
                obList.add(tm);
            }
            tblPackage.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }


    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void clearFields() {
        txtType.clear();
        txtDate.clear();
        txtWeight.clear();
        comboId.setItems(null);
        comboRoute.setItems(null);
        comboDescription.setItems(null);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

        String companyId = comboId.getValue();
        String typeOfGood = txtType.getText();
        double weight = Double.parseDouble(txtWeight.getText());
        String vehicleId = comboVehicleId.getValue();
        Date date = Date.valueOf(txtDate.getText());
        String routId = comboRoute.getValue();
        double shipmentCost = weight * 500;

        try {
            System.out.println("Come to try catch1");
            String currentId = packageBO.getPackageId();
            System.out.println("is ok1");
            String availableId = null;
            if (currentId != null) {
                String[] split = currentId.split("P");
                int idNum = Integer.parseInt(split[1]);
                availableId = "P" + ++idNum;
            } else {
                availableId = "P1";
            }

            System.out.println("is ok2");

            String currentTrackingNUmber = packageBO.getTrackingNumber();
            System.out.println("is ok3");
            String availableNumber = null;
            if (currentTrackingNUmber != null) {
                String[] split = currentTrackingNUmber.split("TR");
                int idNum = Integer.parseInt(split[1]);
                availableNumber = "TR" + ++idNum;
            } else {
                availableNumber = "TR1";

            }
            System.out.println("is ok4");
            String currentShipmentId = shipmentBO.getCurrentShipmenId();
            System.out.println("is ok5");
            String availableShipmentId = null;
            if (currentShipmentId != null) {
                String[] split = currentShipmentId.split("S");
                int idNum = Integer.parseInt(split[1]);
                availableShipmentId = "S" + ++idNum;
            } else {
                availableShipmentId = "S1";
            }

            System.out.println("is ok6");

            String destination = routeBO.getDestination(routId);
            System.out.println("is ok7");
            String mode = getMode(destination);
            System.out.println("is ok8");


            double distance = Double.parseDouble(routeBO.getDistance(routId));
            System.out.println("is ok9");
            String description = routeBO.getDescription(routId);
            System.out.println("is ok10");

            Route route = new Route(routId, "Panadura", destination, description, distance);

            //save package transaction
            Connection connection = Dbconnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            try {
                System.out.println("Invoke to first statement");
                System.out.println(availableShipmentId);
                boolean isSave = shipmentBO.addShipment(new ShipmentDTO(availableShipmentId, shipmentCost, routId));
                if (!isSave) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    new Alert(Alert.AlertType.ERROR, "Package placed unsuccessfully").show();
                    return;
                }
                System.out.println("invoke to statement1");
                System.out.println(availableId+" , "+availableNumber);
                boolean isSaved = packageBO.addPackage(new PackageDTO(availableId, availableNumber, companyId, typeOfGood, weight, mode, date, availableShipmentId));
                if (!isSaved) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    new Alert(Alert.AlertType.ERROR, "Package placed unsuccessfully").show();
                    return;
                }
                System.out.println("Invoke to statement2");
                boolean isOk = deliveryDetailBO.saveDeliveryDetail(new DeliveryDetailDTO(availableShipmentId, vehicleId, destination));
                if (!isOk) {
                    connection.rollback();
                    new Alert(Alert.AlertType.ERROR, "Package placed unsuccessfully").show();
                    return;
                }
                System.out.println("Invoke to statement3");
                boolean isUpdated = vehicleBO.updateVehicleDistance(vehicleId, distance);
                if (!isUpdated) {
                    connection.rollback();
                    new Alert(Alert.AlertType.ERROR, "Package placed unsuccessfully").show();
                    return;
                }
                System.out.println("Invoke to statement4");
                double currentDistance = vehicleToBeServicedBO.getCurrentDistance(vehicleId);
                if (currentDistance >= 5000) {
                    System.out.println("get current distance ?");
                    String title = "Vehicle Service Information";
                    String body = "Vehicle " + vehicleId + ",Your service time has come";
                    DriverDTO dr = driverBO.getDriver(vehicleId);
                    System.out.println("dr :" + dr);
                    String email = dr.getEmail();
                    System.out.println(email);
                    Mail mail = new Mail();
                    mail.setMail(email, title, body);
                    System.out.println("Send successfull");
                }
                if (currentDistance >= 10000) {
                    String title = "Tyre Replacement Information";
                    String body = "Vehicle " + vehicleId + ",Your Tyre Replacement has come";
                    DriverDTO dr = driverBO.getDriver(vehicleId);
                    String email = dr.getEmail();
                    Mail mail = new Mail();
                    mail.setMail(email, title, body);
                }
                if (currentDistance == 10100) {
                    vehicleToBeServicedBO.clearDistance(vehicleId);
                }
                System.out.println("Invoke to this");
                boolean isUpdate = vehicleToBeServicedBO.updateCurrentDistance(distance, vehicleId);
                if (!isUpdate) {
                    connection.rollback();
                    new Alert(Alert.AlertType.ERROR, "Package placed unsuccessfully").show();
                    return;
                }
                System.out.println("Commit");
                connection.commit();
                new Alert(Alert.AlertType.CONFIRMATION, "Package placed successfully").show();
            } catch (Exception e) {
                connection.rollback();
                new Alert(Alert.AlertType.ERROR, "Package placed unsuccessfully").show();
            }
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public  String getMode(String destination) {
        if (destination.equals("Colombo")){
            return "Ship";
        } else if (destination.equals("Katunayake")){
            return "AirPlane";
        } else {
            return "Ship";
        }
    }

    private String getTransportMode(String routId) {
        try {
            String destination = routeBO.getDestination(routId);
            return destination;
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage(),ButtonType.OK).show();
        }
        return null;
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String companyId = comboId.getValue();
        String typeOfGood = txtType.getText();
        double weight = Double.parseDouble(txtWeight.getText());
        String mode = comboVehicleId.getValue();
        Date date = Date.valueOf(txtDate.getText());
        String routId = comboRoute.getValue();
        String packId = txtSearchBar.getText();

    }
    public void comboIdOnAction(ActionEvent actionEvent) {
    }

    public void txtWeightOnAction(ActionEvent actionEvent) {
        txtDate.requestFocus();
    }

    private void setComboVehicleId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> vehicleId = vehicleBO.getCurrentVehicleList();
            for (String id : vehicleId){
                System.out.println(id);
                obList.add(id);
            }
            comboVehicleId.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void setComboRoute() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> idList = routeBO.getIdList();
            for (String id : idList){
                obList.add(id);
            }
            comboRoute.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void setDate() {
        lblDatePicker.setText(String.valueOf(LocalDate.now()));
        txtDate.setText(String.valueOf(LocalDate.now()));
    }

    private void setComboDescription() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add("With Traffic");
        obList.add("Without Traffic");
        comboDescription.setItems(obList);
    }

    private void setTransportMode() {
        ObservableList<String> modes = FXCollections.observableArrayList();
        modes.add("Flight");
        modes.add("Ship");
        comboVehicleId.setItems(modes);
    }

    private void setCombo() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> companyId = clientBO.getCompanyIdList();
            for (String id : companyId){
                obList.add(id);
            }
            comboId.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void txtDateOnAction(ActionEvent actionEvent) {

    }

    public void txtTypeOnAction(ActionEvent actionEvent) {
        txtWeight.requestFocus();
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String packageId = txtSearchBar.getText();
        ObservableList<Object> items = FXCollections.observableArrayList();
        try {
            PackageDTO packageDTO = packageBO.getPackageObject(packageId);
            txtType.setText(packageDTO.getDeliveryType());
            txtWeight.setText(String.valueOf(packageDTO.getWeight()));
            txtDate.setText(String.valueOf(packageDTO.getBorrowDAte()));
            ObservableList<String> routeId = shipmentBO.getRouteIdList(packageDTO.getShipmentId());
            comboRoute.setItems(routeId);
            comboDescription.setPromptText(packageDTO.getTypeOfGood());
            comboVehicleId.setPromptText(packageBO.getVehicleId(packageId));

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage(),ButtonType.CANCEL).show();
        }

    }

    public void txtLocationOnAction(ActionEvent actionEvent) {
        txtDestination.requestFocus();
    }

    public void txtDestinationOnAction(ActionEvent actionEvent) {
        txtDistance.requestFocus();
    }

    public void txtDistanceOnAction(ActionEvent actionEvent) {
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        newPane.setVisible(false);
    }
    public void comboDescriptionOnAction(ActionEvent actionEvent) {
        txtDistance.requestFocus();
    }

    public void btnRouteAddOnAction(ActionEvent actionEvent) {
        newPane.setVisible(true);
    }

    public void btnRouteClearOnAction(ActionEvent actionEvent) {
        txtLocation.clear();
        txtDestination.clear();
        txtDistance.clear();
    }

    public void btnRouteSaveOnAction(ActionEvent actionEvent) {
        String location = txtLocation.getText();
        String destination = txtDestination.getText();
        String description = comboDescription.getValue();
        double distance = Double.parseDouble(txtDistance.getText());
        try {
            String currentRouteId = routeBO.getRouteId();
            String nextAvailableId = null;
            if (currentRouteId != null){
                if (currentRouteId != null){
                    String[] split = currentRouteId.split("R");
                    int idNum = Integer.parseInt(split[1]);
                    nextAvailableId = "R" + ++idNum;
                } else {
                    nextAvailableId = "R1";
                }
            }

            if (isValided()) {
                boolean isSaved = routeBO.addRoute(new RouteDTO(nextAvailableId, location, destination, description, distance));
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Route saved successfully").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Route saved unsuccessfully").show();
                }
            }
            } catch(SQLException e){
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private boolean isValided() {
        if (!Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.NAME,txtLocation)) return false;
        if (!Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.NAME,txtDestination)) return false;
        if (!Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.DISTANCE,txtDistance)) return false;
        return true;
    }

    public void btnRouteUpdateOnAction(ActionEvent actionEvent) {

    }

    public void txtTypesKeyReleasedOnAction(KeyEvent keyEvent) {
        Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.WORD,txtType);
    }

    public void txtWeightKeyReleasedOnAction(KeyEvent keyEvent) {
        Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.WEIGHT,txtWeight);
    }

    public void txtDateKeyReleasedOnAction(KeyEvent keyEvent) {
        Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.DATE,txtDate);
    }

    public void txtLocationKeyReleasedOnAction(KeyEvent keyEvent) {
        Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.NAME,txtLocation);
    }

    public void txtDestinationKeyReleasedOnAction(KeyEvent keyEvent) {
        Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.NAME,txtDestination);
    }

    public void txtDistanceKeyReleasedOnAction(KeyEvent keyEvent) {
        Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.DISTANCE,txtDistance);
    }
}
