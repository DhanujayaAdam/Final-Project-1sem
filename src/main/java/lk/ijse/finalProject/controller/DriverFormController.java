package lk.ijse.finalProject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import lk.ijse.finalProject.DB.Dbconnection;
import lk.ijse.finalProject.model.Driver;
import lk.ijse.finalProject.model.Vehicle;
import lk.ijse.finalProject.repository.DriverRepo;
import lk.ijse.finalProject.repository.VehicleRepo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverFormController {

    public AnchorPane rootNode;
    public AnchorPane scrollPane;
    public Pane node;
    public JFXButton txtTips;
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    public JFXButton btnAlert;
    public Hyperlink hplName1;
    public Hyperlink hplName2;
    public Hyperlink hplName3;
    public Hyperlink hplName4;
    public Hyperlink hplName5;
    public Hyperlink hplName6;
    public Hyperlink hplName7;
    public Hyperlink hplName8;
    public Label lblId1;
    public Label lblId2;
    public Label lblId3;
    public Label lblId4;
    public Label lblId5;
    public Label lblId6;
    public Label lblId7;
    public Label lblDriver1;
    public Circle profilePicture;
    public Label userName;
    public TextField txtSearchBar;
    public Label lblDatePicker;
    public Circle profilePicture1;
    public Circle profilePicture2;
    public Circle profilePicture3;
    public Circle profilePicture4;
    public Circle profilePicture5;
    public Circle profilePicture6;
    public Circle profilePicture7;
    @FXML
    BorderPane borderPane;

    public void initialize(){
        setProfilePicture();
        setName();
        setId();

    }

    private void setId() {
        try {
            List<String> id = DriverRepo.getId();
            if(id.size() < 1){
                lblId1.setText("no data");
            } else {
                lblId1.setText(id.get(0));
            }
            if (id.size() < 2) {
                lblId2.setText("no data");
            } else {
                lblId2.setText(id.get(1));
            }
            if (id.size() < 3) {
                lblId3.setText("no data");
            } else {
                lblId3.setText(id.get(2));
            }
            if (id.size() < 4) {
                lblId4.setText("no data");
            } else {
                lblId4.setText(id.get(3));
            }
            if (id.size() < 5) {
                lblId5.setText("no data");
            } else {
                lblId5.setText(id.get(4));
            }
            if (id.size() < 6) {
                lblId6.setText("no data");
            } else {
                lblId6.setText(id.get(5));
            }
            if (id.size() < 7){
                lblId7.setText("no data");
            } else {
                lblId7.setText(id.get(6));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void setName() {
        try {
            List<String> nameList = DriverRepo.getName() ;
            if(nameList.size() < 1){
                hplName1.setText("no data");
            } else {
                hplName1.setText(nameList.get(0));
            }
            if (nameList.size() < 2) {
                hplName2.setText("no data");
            } else {
                hplName2.setText(nameList.get(1));
            }
            if (nameList.size() < 3) {
                hplName3.setText("no data");
            } else {
                hplName3.setText(nameList.get(2));
            }
            if (nameList.size() < 4) {
                hplName4.setText("no data");
            } else {
                hplName4.setText(nameList.get(3));
            }
            if (nameList.size() < 5) {
                lblId4.setText("no data");
            } else {
                hplName5.setText(nameList.get(4));
            }
            if (nameList.size() < 6) {
                hplName6.setText("no data");
            } else {
                hplName6.setText(nameList.get(5));
            }
            if (nameList.size() < 7){
                hplName7.setText("no data");
            } else {
                hplName7.setText(nameList.get(6));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }



    private void setProfilePicture() {
        Image image = new Image(String.valueOf(this.getClass().getResource("/image/humen1.jpeg")));
        profilePicture.setFill(new ImagePattern(image));

        Image image1 = new Image(String.valueOf(this.getClass().getResource("/driver/driver1.jpg")));
        Image image2 = new Image(String.valueOf(this.getClass().getResource("/driver/humen3.jpeg")));
        Image image3 = new Image(String.valueOf(this.getClass().getResource("/driver/humen4.jpeg")));
        Image image4 = new Image(String.valueOf(this.getClass().getResource("/driver/manager1.jpg")));
        Image image5 = new Image(String.valueOf(this.getClass().getResource("/driver/humen5.jpg")));
        Image image6 = new Image(String.valueOf(this.getClass().getResource("/driver/manager1.jpg")));
        Image image7 = new Image(String.valueOf(this.getClass().getResource("/driver/driver2.jpg")));
        Image image8 = new Image(String.valueOf(this.getClass().getResource("/driver/dispatcher.jpeg")));

//       BackgroundImage bgImage = new BackgroundImage(
//               image,
//               BackgroundRepeat.REPEAT,
//               BackgroundRepeat.REPEAT,
//               BackgroundPosition.CENTER,
//               new BackgroundSize(bgX,bgY,true,true,true,true)
//               );
//        ImageIcon imageIcon = new ImageIcon(String.valueOf(image1));
//        lblDriver1.setBackground(new Background(bgImage));



       profilePicture1.setFill(new ImagePattern(image2));
       profilePicture2.setFill(new ImagePattern(image3));
       profilePicture3.setFill(new ImagePattern(image4));
       profilePicture4.setFill(new ImagePattern(image5));
       profilePicture5.setFill(new ImagePattern(image6));
       profilePicture6.setFill(new ImagePattern(image7));
       profilePicture7.setFill(new ImagePattern(image8));
    }

    public void btnClearOnAction(ActionEvent event) {

    }


    public void hplViewDriverOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane node = FXMLLoader.load(this.getClass().getResource("/view/driverViewForm.fxml"));
        Scene scene = new Scene(node);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Driver Info");
        stage.centerOnScreen();
    }

    public void btnNextOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/vehicleSelection.fxml"));
        this.node.getChildren().clear();
        this.node.getChildren().add(rootNode);
    }
    public void btnTipsOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/view/driverTipsForm.fxml"));
        this.node.getChildren().clear();
        this.node.getChildren().add(pane);
    }
    public void btnAddOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/view/driverAddForm.fxml"));
        this.node.getChildren().clear();
        this.node.getChildren().add(pane);
    }
    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/view/driverUpdateForm.fxml"));
        this.node.getChildren().clear();;
        this.node.getChildren().add(pane);
    }

    public void btnAlertOnAction(ActionEvent actionEvent) throws  SQLException, JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/report/driverDetail.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String,Object> data = new HashMap<>();
        data.put("Date" ,lblDatePicker.getText());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, data, Dbconnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }

    public void sendParent(BorderPane parent) {
        borderPane=parent;
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
    }

    public void hplName1OnAction(ActionEvent actionEvent) {
        String id = lblId1.getText();
        try {
            Driver driver = DriverRepo.getDetail(id);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/driverViewForm.fxml"));
            loader.load();
            DriverViewFormController driverView = loader.getController();
            driverView.sendDriver(driver);
            BorderPane pane = loader.getRoot();
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setTitle("Driver View");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (SQLException | IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void hplName2OnAction(ActionEvent actionEvent) {
        String id = lblId2.getText();
        try {
            Driver driver = DriverRepo.getDetail(id);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/driverViewForm.fxml"));
            loader.load();
            DriverViewFormController driverView = loader.getController();
            driverView.sendDriver(driver);
            BorderPane pane = loader.getRoot();
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setTitle("Driver View");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (SQLException | IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void hplName3Onaction(ActionEvent actionEvent) {
        String id = lblId3.getText();
        try {
            Driver driver = DriverRepo.getDetail(id);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/driverViewForm.fxml"));
            loader.load();
            DriverViewFormController driverView = loader.getController();
            driverView.sendDriver(driver);
            BorderPane pane = loader.getRoot();
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setTitle("Driver View");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (SQLException | IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void hplName4OnAction(ActionEvent actionEvent) {
        String id = lblId4.getText();
        try {
            Driver driver = DriverRepo.getDetail(id);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/driverViewForm.fxml"));
            loader.load();
            DriverViewFormController driverView = loader.getController();
            driverView.sendDriver(driver);
            BorderPane pane = loader.getRoot();
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setTitle("Driver View");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (SQLException | IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void hplName5OnAction(ActionEvent actionEvent) {
        String id = lblId6.getText();
        try {
            Driver driver = DriverRepo.getDetail(id);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/driverViewForm.fxml"));
            loader.load();
            DriverViewFormController driverView = loader.getController();
            driverView.sendDriver(driver);
            BorderPane pane = loader.getRoot();
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setTitle("Driver View");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (SQLException | IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void hplName6OnAction(ActionEvent actionEvent) {
        String id = lblId6.getText();
        try {
            Driver driver = DriverRepo.getDetail(id);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/driverViewForm.fxml"));
            loader.load();
            DriverViewFormController driverView = loader.getController();
            driverView.sendDriver(driver);
            BorderPane pane = loader.getRoot();
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setTitle("Driver View");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (SQLException | IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void hplName7OnAction(ActionEvent actionEvent) {
        String id = lblId7.getText();
        try {
            Driver driver = DriverRepo.getDetail(id);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/driverViewForm.fxml"));
            loader.load();
            DriverViewFormController driverView = loader.getController();
            driverView.sendDriver(driver);
            BorderPane pane = loader.getRoot();
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setTitle("Driver View");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (SQLException | IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
}
