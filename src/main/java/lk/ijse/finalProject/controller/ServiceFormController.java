package lk.ijse.finalProject.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import lk.ijse.finalProject.bo.custom.VehicleBO;
import lk.ijse.finalProject.bo.custom.impl.VehicleBOImpl;
import lk.ijse.finalProject.db.Dbconnection;
import lk.ijse.finalProject.dao.custom.impl.*;
import lk.ijse.finalProject.entity.*;
import lk.ijse.finalProject.entity.tm.ServiceTm;
import lk.ijse.finalProject.util.Regex;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ServiceFormController implements Initializable {
    public TextField txtSearchBar;
    public TableColumn<?,?> clmVehicleId;
    public TableColumn<?,?> clmDescription;
    public TableColumn<?,?> clmServiceCenter;
    public TableColumn<?,?> clmServiceCost;
    public TableColumn<?,?> clmServiceDate;
    public TableColumn<?,?> clmEdit;
    public TextField txtDescription;
    public JFXComboBox<String> comboVehicleId;
    public JFXComboBox<String> comboServiceType;
    public Circle profilePicture1;
    public Circle profilePicture2;
    public Circle profilePicture3;
    public Circle profilePicture4;
    public Circle profilePicture5;
    public TextField txtDate;
    public TextField txtAmount;
    public JFXComboBox<String> comboPayment;
    public JFXComboBox<String> comboServiceCenter;
    public TextField txtCenter;
    public TextField txtPhone;
    public TextField txtLocation;
    public TextField txtEmail;
    public Label userName;
    public Circle profilePicture;
    public Label lblDatePicker;
    public Pane newPane;
    public Pane oldPane;
    public TableView<ServiceTm> tblService;
    public Hyperlink center1;
    public Label tel1;
    public Hyperlink center2;
    public Label tel2;
    public Hyperlink center3;
    public Label tel3;
    public Hyperlink center4;
    public Label tel4;
    public Hyperlink center5;
    public Label tel5;
    VehicleBO vehicleBO = new VehicleBOImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setComboVeicleId();
        setComboServiceType();
        setComboServiceCenter();
        setComboPaymentMethod();
        setTable();
        setCellValueFactory();
        setProfileAndValues();
    }

    private void setProfileAndValues() {
        Image dp = new Image(getClass().getResourceAsStream("/image/humen1.jpeg"));
        profilePicture.setFill(new ImagePattern(dp));
        lblDatePicker.setText(String.valueOf(LocalDate.now()));

        Image image = new Image(getClass().getResourceAsStream("/serviceCenterProfile/car-and-vehicle-logo-for-your-needs-such-car-shop-service-store-car-repair-free-vector.jpg"));
        profilePicture1.setFill(new ImagePattern(image));
        profilePicture2.setFill(new ImagePattern(image));
        profilePicture3.setFill(new ImagePattern(image));
        profilePicture4.setFill(new ImagePattern(image));
        profilePicture5.setFill(new ImagePattern(image));

        try {
            List<String> nameList = ServiceCenterDAOImpl.getName();
            if(nameList.size() < 1){
                center1.setText("no data");
            } else {
                center1.setText(nameList.get(0));
            }
            if (nameList.size() < 2) {
                center2.setText("no data");
            } else {
                center2.setText(nameList.get(1));
            }
            if (nameList.size() < 3) {
                center3.setText("no data");
            } else {
                center3.setText(nameList.get(2));
            }
            if (nameList.size() < 4) {
                center4.setText("no data");
            } else {
                center4.setText(nameList.get(3));
            }
            if (nameList.size() < 5) {
                center5.setText("no data");
            } else {
                center5.setText(nameList.get(4));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        try {
            List<String> nameList = ServiceCenterDAOImpl.getPhone();
            if(nameList.size() < 1){
                tel1.setText("no data");
            } else {
                tel1.setText(nameList.get(0));
            }
            if (nameList.size() < 2) {
                tel2.setText("no data");
            } else {
                tel2.setText(nameList.get(1));
            }
            if (nameList.size() < 3) {
                tel3.setText("no data");
            } else {
                tel3.setText(nameList.get(2));
            }
            if (nameList.size() < 4) {
                tel4.setText("no data");
            } else {
                tel4.setText(nameList.get(3));
            }
            if (nameList.size() < 5) {
                tel5.setText("no data");
            } else {
                tel5.setText(nameList.get(4));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        clmDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        clmServiceDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        clmServiceCenter.setCellValueFactory(new PropertyValueFactory<>("centerId"));
        clmServiceCost.setCellValueFactory(new PropertyValueFactory<>("amount"));
        clmVehicleId.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));
    }

    private void setTable() {
        ObservableList<ServiceTm> obList = FXCollections.observableArrayList();
        try {
            List<ServiceTable> allDetail = ServiceDAOImpl.getAllDetail();
            for (ServiceTable detail : allDetail){
                ServiceTm tm = new ServiceTm(
                        detail.getVehicleId(),
                        detail.getDescription(),
                        detail.getDate(),
                        detail.getCenterId(),
                        detail.getAmount()
                );
                obList.add(tm);
            }
            tblService.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void setComboPaymentMethod() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add(0,"Card");
        obList.add(1,"Deposit");
        obList.add(2,"Cash");
        comboPayment.setItems(obList);
    }

    private void setComboServiceCenter() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> idList = ServiceCenterDAOImpl.getId();
            for (String id : idList){
                obList.add(id);
            }
            comboServiceCenter.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void setComboServiceType() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> typeList = ServiceScheduleDAOImpl.getType();
            for (String type : typeList){
                obList.add(type);
            }
            comboServiceType.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void setComboVeicleId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> vehicleId = vehicleBO.getCurrentVehicleList();
            for (String vehiId : vehicleId){
                obList.add(vehiId);
            }
            comboVehicleId.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String text = txtSearchBar.getText();
        try {
            Service service = ServiceDAOImpl.getAll();
            txtDescription.setText(service.getDescription());
            txtDate.setText(String.valueOf(service.getDate()));
            txtAmount.setText("Check Your Service Bill");

            ServiceCenter centers = ServiceCenterDAOImpl.getAll(text);
            txtCenter.setText(centers.getName());
            txtLocation.setText(centers.getLocation());
            txtPhone.setText(centers.getTel());
            txtEmail.setText(centers.getEmail());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void txtDateOnAction(ActionEvent actionEvent) {
        txtAmount.requestFocus();
    }

    public void txtDescriptionOnAction(ActionEvent actionEvent) {
        txtDate.requestFocus();
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtDescription.clear();
        txtAmount.clear();
        txtDate.clear();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String vehicleId = comboVehicleId.getValue();
        String serviceType = comboServiceType.getValue();
        String center = comboServiceCenter.getValue();
        String method = comboPayment.getValue();
        String description = txtDescription.getText();
        Date date = Date.valueOf(txtDate.getText());
        double amount = Double.parseDouble(txtAmount.getText());
        try {
            String currentId = ServiceDAOImpl.getAvailableId();
            String availableId = ServiceDAOImpl.getCurrentId(currentId);
            Service service = new Service(availableId,vehicleId,serviceType,description,date,center);

            String currentPaymentId1 = PaymentDAOImpl.getCurrentId();
            String nextId = PaymentDAOImpl.getNextId(currentPaymentId1);
            Payment payment = new Payment(nextId,center,amount,method,date);

            ServisePayment sp = new ServisePayment(service,payment);
            boolean isSaved = ServicePaymentRepo.save(sp);
            if (isSaved){
                setTable();
                setCellValueFactory();
                new Alert(Alert.AlertType.CONFIRMATION,"Service saved successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR,"Service saved unsuccessfully").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtSearchBar.getText();
        String vehicleId = comboVehicleId.getValue();
        String serviceType = comboServiceType.getValue();
        String center = comboServiceCenter.getValue();
        String method = comboPayment.getValue();
        String description = txtDescription.getText();
        Date date = Date.valueOf(txtDate.getText());
        double amount = Double.parseDouble(txtAmount.getText());
        try {
            String id1 = PaymentDAOImpl.getId(date);
            Service service = new Service(id,vehicleId,serviceType,description,date,center);
            Payment payment = new Payment(id1,center,amount,method,date);
            ServisePayment sp = new ServisePayment(service,payment);
            boolean isUpdated = ServicePaymentRepo.update(sp);
            if(isUpdated){
                setTable();
                setCellValueFactory();
                new Alert(Alert.AlertType.CONFIRMATION,"Service updated successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR,"Service update unsuccessfully").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    public void txtTypeOnAction(ActionEvent actionEvent) {
    }

    public void btnAddCenterOnAction(ActionEvent actionEvent) {
        newPane.setVisible(true);
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        newPane.setVisible(false);

    }

    public void txtCenterOnAction(ActionEvent actionEvent) {
        txtLocation.requestFocus();
    }

    public void btnClearCenterOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void clearFields() {
        txtCenter.clear();
        txtLocation.clear();
        txtPhone.clear();
        txtEmail.clear();
    }

    public void btnSaveCenterOnAction(ActionEvent actionEvent) {
        String center = txtCenter.getText();
        String location = txtLocation.getText();
        String phone = txtPhone.getText();
        String email = txtEmail.getText();
        try {
            String currentId = ServiceCenterDAOImpl.getCurrentId();
            String avilableId = ServiceCenterDAOImpl.getAvilableId(currentId);
            ServiceCenter serviceCenter = new ServiceCenter(avilableId,center,location,phone,email);
            if (isValided()) {
                boolean isSaved = ServiceCenterDAOImpl.saveCenter(serviceCenter);
                if (isSaved) {
                    clearFields();
                    new Alert(Alert.AlertType.CONFIRMATION, "Service center saved successfully").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Service center saved unsuccessfully").show();
                }
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void btnUpdateCenterOnAction(ActionEvent actionEvent) {
        String id = txtSearchBar.getText();
        String center = txtCenter.getText();
        String location = txtLocation.getText();
        String phone = txtPhone.getText();
        String email = txtEmail.getText();
        ServiceCenter serviceCenter = new ServiceCenter(id,center,location,phone,email);
        try {
            if (isValided()) {
                clearFields();
                boolean isUpdated = ServiceCenterDAOImpl.update(serviceCenter);
                if (isUpdated) {
                    clearFields();
                    new Alert(Alert.AlertType.CONFIRMATION, "Service center update successfully").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Service center update unsuccessfully").show();
                }
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private boolean isValided() {
        if (!Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.WORD,txtCenter)) return false;
        if (!Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.WORD,txtLocation)) return false;
        if (!Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.PHONE,txtPhone)) return false;
        if (!Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.EMAIL,txtEmail)) return false;
        return true;
    }

    public void txtPhoneOnAction(ActionEvent actionEvent) {
        txtEmail.requestFocus();

    }

    public void txtLocationOnAction(ActionEvent actionEvent) {
        txtPhone.requestFocus();
    }

    public void txtEmailOnAction(ActionEvent actionEvent) {
    }

    public void dateKeyReleasedOnAction(KeyEvent keyEvent) {
        Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.DATE,txtDate);
    }

    public void descriptionKeyReleasedOnAction(KeyEvent keyEvent) {
        Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.WORD,txtDescription);
    }

    public void amountKeyReleasedOnAction(KeyEvent keyEvent) {
        Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.PRICE,txtAmount);
    }

    public void centersKeyReleasedAction(KeyEvent keyEvent) {
        Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.WORD,txtCenter);
    }

    public void phoneOnKeyReleasedAction(KeyEvent keyEvent) {
        Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.PHONE,txtPhone);
    }

    public void locationOnKeyReleasedAction(KeyEvent keyEvent) {
        Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.WORD,txtLocation);
    }

    public void emailOnKeyReleasedAction(KeyEvent keyEvent) {
        Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.EMAIL,txtEmail);
    }

    public void btnGenerateReportOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/report/driverDetail.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Vehicle vehicle = VehicleDAOImpl.getValues((comboVehicleId.getValue()));

        Map<String,Object> data = new HashMap<>();
        data.put("Date" ,lblDatePicker.getText());
        data.put("Vehicle",vehicle.getName());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, data, Dbconnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }
}
