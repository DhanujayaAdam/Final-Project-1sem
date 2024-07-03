package lk.ijse.finalProject.controller;

import com.jfoenix.controls.JFXButton;
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
import lk.ijse.finalProject.bo.BOFactory;
import lk.ijse.finalProject.bo.custom.*;
import lk.ijse.finalProject.bo.custom.impl.*;
import lk.ijse.finalProject.dao.custom.ServiceCenterDAO;
import lk.ijse.finalProject.db.Dbconnection;
import lk.ijse.finalProject.dao.custom.impl.*;
import lk.ijse.finalProject.dto.*;
import lk.ijse.finalProject.entity.*;
import lk.ijse.finalProject.entity.tm.ServiceTm;
import lk.ijse.finalProject.util.Regex;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ServiceFormController implements Initializable {
    public TextField txtSearchBar;
    public TableColumn<?, ?> clmVehicleId;
    public TableColumn<?, ?> clmDescription;
    public TableColumn<?, ?> clmServiceCenter;
    public TableColumn<?, ?> clmServiceCost;
    public TableColumn<?, ?> clmServiceDate;
    public TableColumn<?, ?> clmEdit;
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
    public JFXButton btnGenerateReport;
    VehicleBO vehicleBO = (VehicleBO) BOFactory.getBoFactory().getInstance(BOFactory.BoType.VEHICLE);
    ServiceCenterBO centerBO = (ServiceCenterBO) BOFactory.getBoFactory().getInstance(BOFactory.BoType.SERVICE_CENTER);
    QueryBO queryBO = (QueryBO) BOFactory.getBoFactory().getInstance(BOFactory.BoType.JOIN_QUERY);
    ServiceSheduleBO serviceSheduleBO = (ServiceSheduleBO) BOFactory.getBoFactory().getInstance(BOFactory.BoType.SERVICE_SCHEDULE);
    ServiceBO serviceBO = (ServiceBO) BOFactory.getBoFactory().getInstance(BOFactory.BoType.SERVICE);
    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getInstance(BOFactory.BoType.PAYMENT);

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
            List<String> nameList = centerBO.getName();
            if (nameList.size() < 1) {
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
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        try {
            List<String> nameList = centerBO.getPhone();
            if (nameList.size() < 1) {
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
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
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
            List<ServiceTableDTO> allDetail = queryBO.getAllDetail();
            for (ServiceTableDTO detail : allDetail) {
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
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setComboPaymentMethod() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add(0, "Card");
        obList.add(1, "Deposit");
        obList.add(2, "Cash");
        comboPayment.setItems(obList);
    }

    private void setComboServiceCenter() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> idList = centerBO.getIds();
            for (String id : idList) {
                obList.add(id);
            }
            comboServiceCenter.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setComboServiceType() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> typeList = serviceSheduleBO.getType();
            for (String type : typeList) {
                obList.add(type);
            }
            comboServiceType.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setComboVeicleId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> vehicleId = vehicleBO.getCurrentVehicleList();
            for (String vehiId : vehicleId) {
                obList.add(vehiId);
            }
            comboVehicleId.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String text = txtSearchBar.getText();
        try {
            ServiceDTO service = serviceBO.getServiceObject(text);
            txtDescription.setText(service.getDescription());
            txtDate.setText(String.valueOf(service.getDate()));
            txtAmount.setText("Check Your Service Bill");

            ServiceCenterDTO centers = centerBO.getCenterObject(text);
            txtCenter.setText(centers.getName());
            txtLocation.setText(centers.getLocation());
            txtPhone.setText(centers.getTel());
            txtEmail.setText(centers.getEmail());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
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
        try {
            boolean isSave = savePayment();
            if (isSave){
                new Alert(Alert.AlertType.CONFIRMATION,"Payment successfully").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Payment unsuccessfully").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public boolean savePayment() throws SQLException {
        String vehicleId = comboVehicleId.getValue();
        String serviceType = comboServiceType.getValue();
        String center = comboServiceCenter.getValue();
        String method = comboPayment.getValue();
        String description = txtDescription.getText();
        Date date = Date.valueOf(txtDate.getText());
        double amount = Double.parseDouble(txtAmount.getText());
        try {
            String currentId = serviceBO.getServiceId();
            String availableId = null;
            if (center != null) {
                if (currentId != null) {
                    String[] split = currentId.split("SE");
                    int idNum = Integer.parseInt(split[1]);
                    availableId = "SE" + ++idNum;
                }
                availableId = "SE1";
            }

            String currentPaymentId = paymentBO.getPaymentId();
            String nextId = null;
            if (currentPaymentId != null) {
                String[] split = currentPaymentId.split("T");
                int idNum = Integer.parseInt(split[1]);
                nextId = "T" + ++idNum;
            } else {
                nextId = "T1";
            }
            //Payment transaction
            Connection connection = Dbconnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            System.out.println("statement 1");
            boolean isSaved = serviceBO.addService(new ServiceDTO(availableId, vehicleId, serviceType, description, date, center));
                if (!isSaved) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            boolean isSave = paymentBO.addPayment(new PaymentDTO(nextId, center, amount, method, date));
            System.out.println("statement 1");
            PaymentDTO paymentDTO = new PaymentDTO(nextId, center, amount, method, date);
            System.out.println(paymentDTO);
                if (!isSave) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            System.out.println("statement 2");
                connection.commit();
                connection.setAutoCommit(true);
                return true;
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        return false;
    }


    public void btnUpdateOnAction(ActionEvent actionEvent) {
        new Alert(Alert.AlertType.INFORMATION,"Can't update payment").show();

    }

    public void txtTypeOnAction(ActionEvent actionEvent) {
    }

    public void btnAddCenterOnAction(ActionEvent actionEvent) {
        newPane.setVisible(true);
        btnGenerateReport.setVisible(false);
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        newPane.setVisible(false);
        btnGenerateReport.setVisible(true);
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
            String currentId = centerBO.getCenterId();
            String avilableId = null;
            if (currentId != null){
                if (currentId != null){
                    String[] split = currentId.split("SCI");
                    int idNum = Integer.parseInt(split[1]);
                    avilableId = "SCI" + ++idNum;
                }
                avilableId = "SCI1";
            }
            if (isValided()) {
                boolean isSaved = centerBO.addCenter(new ServiceCenterDTO(avilableId,center,location,phone,email));
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

        try {
            if (isValided()) {
                clearFields();
                boolean isUpdated = centerBO.updateCenter(new ServiceCenterDTO(id,center,location,phone,email));
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

        VehicleDTO vehicle = vehicleBO.getVehicleObject((comboVehicleId.getValue()));

        Map<String,Object> data = new HashMap<>();
        data.put("Date" ,lblDatePicker.getText());
        data.put("Vehicle",vehicle.getName());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, data, Dbconnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }
}
