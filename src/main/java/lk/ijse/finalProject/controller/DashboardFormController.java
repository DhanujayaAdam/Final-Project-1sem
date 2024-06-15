package lk.ijse.finalProject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import lk.ijse.finalProject.DB.Dbconnection;
import lk.ijse.finalProject.repository.ClientRepo;
import lk.ijse.finalProject.repository.PackageRepo;
import lk.ijse.finalProject.repository.PaymentRepo;

import javax.swing.text.StyledEditorKit;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {
    public Label lblDatePicker;
    public Circle profilePicture;
    public Label userName;
    public Circle profilePicture1;
    public LineChart<?,?> lineChart;
    public BorderPane parent;
    public Pane rootNode;
    public JFXButton btnDashboard;
    public JFXButton btnVehicle;
    public JFXButton btnDriver;
    public JFXButton btnPackage;
    public JFXButton btnService;
    public JFXButton btnCompany;
    public JFXButton btnLogout;
    public Circle profilePicture2;
    public Circle profilePicture3;
    public BarChart<?,?>  barChart;
    public TextField txtSearchBar;
    public Hyperlink hplTrackNum1;
    public Label lblCompany1;
    public Hyperlink hplTrackNum2;
    public Label lblCompany2;
    public Hyperlink hplTrackNum3;
    public Label lblCompany3;
    public Hyperlink hplTrackNum4;
    public Label lblCompany4;
    public Hyperlink hplCompany1;
    public Label lblAddress1;
    public Hyperlink hplCompany2;
    public Label lblAddress2;
    public Hyperlink hplCompany3;
    public Label lblAddress3;
    public JFXButton btnMap;
    public Box box;
    public Circle package1;
    public Circle package2;
    public Circle package3;
    public Circle package4;
    public Circle company1;
    public Circle company2;
    public Circle company3;
    public Circle employee1;
    public Circle employee2;
    public Circle employee3;
    public Circle employee4;
    public Circle vehicle1;
    public Circle vehicle2;
    public Circle vehicle3;
    public Circle vehicle4;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCompany();
        setPackage();
        setUserName();
        setProfilePicture();
        setDate();
        //setLineChart();
    }

    private void setLineChart() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> profits = PaymentRepo.getProfit();
            for (String profit : profits){
                obList.add(profit);

            }
            XYChart.Series series1 = new XYChart.Series();
            series1.setName("Profit");
            series1.setData(obList);
            lineChart.getData().add(series1);

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }


//        XYChart.Series series1 = new XYChart.Series();
//        series1.getData().add(new XYChart.Data("1",4));
//        series1.getData().add(new XYChart.Data("2",3));
//        series1.getData().add(new XYChart.Data("3",7));
//        series1.getData().add(new XYChart.Data("4",7));
//        series1.setName("Expenses");
//
//        lineChart.getData().add(series1);

    }

    private void setCompany() {
        try {
            List<String> company = ClientRepo.getCompany();
            if(company.size() < 1){
                hplCompany1.setText("no data");
            } else {
                hplCompany1.setText(company.get(0));
            }
            if (company.size() < 2) {
                hplCompany2.setText("no data");
            } else {
                hplCompany2.setText(company.get(1));
            }
            if (company.size() < 3) {
                hplCompany3.setText("no data");
            } else {
                hplCompany3.setText(company.get(2));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        try {
            List<String> company = ClientRepo.getAddress();
            if(company.size() < 1){
                lblAddress1.setText("no data");
            } else {
                lblAddress1.setText(company.get(0));
            }
            if (company.size() < 2) {
                lblAddress2.setText("no data");
            } else {
                lblAddress2.setText(company.get(1));
            }
            if (company.size() < 3) {
                lblAddress3.setText("no data");
            } else {
                lblAddress3.setText(company.get(2));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void setPackage() {
        try {
            List<String> trackingNumbers = PackageRepo.getTrackingNumbers();
            if(trackingNumbers.size() < 1){
                hplTrackNum1.setText("no data");
            } else {
                hplTrackNum1.setText(trackingNumbers.get(0));
            }
            if (trackingNumbers.size() < 2) {
                hplTrackNum2.setText("no data");
            } else {
                hplTrackNum2.setText(trackingNumbers.get(1));
            }
            if (trackingNumbers.size() < 3) {
                hplTrackNum3.setText("no data");
            } else {
                hplTrackNum3.setText(trackingNumbers.get(2));
            }
            if (trackingNumbers.size() < 4) {
                hplTrackNum4.setText("no data");
            } else {
                hplTrackNum4.setText(trackingNumbers.get(3));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        try {
            List<String> companyId = PackageRepo.getCompanyId();
            if(companyId.size() < 1){
                lblCompany1.setText("no data");
            } else {
                lblCompany1.setText(companyId.get(0));
            }
            if (companyId.size() < 2) {
                lblCompany2.setText("no data");
            } else {
                lblCompany2.setText(companyId.get(1));
            }
            if (companyId.size() < 3) {
                lblCompany3.setText("no data");
            } else {
                lblCompany3.setText(companyId.get(2));
            }
            if (companyId.size() < 4) {
                lblCompany4.setText("no data");
            } else {
                lblCompany4.setText(companyId.get(3));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void setUserName() {

    }


    private void setProfilePicture() {
        Image dp = new Image(String.valueOf(this.getClass().getResource("/image/humen1.jpeg")));
        profilePicture.setFill(new ImagePattern(dp));
        profilePicture1.setFill(new ImagePattern(dp));

        Image vehicle1 = new Image(getClass().getResourceAsStream("/truck/images.jpeg"));
        Image vehicle2 = new Image(getClass().getResourceAsStream("/truck/daf.jpeg"));
        Image vehicle3 = new Image(getClass().getResourceAsStream("/truck/Scania1.jpeg"));
        Image vehicle4 = new Image(getClass().getResourceAsStream("/truck/Scania3.jpg"));

        this.vehicle1.setFill(new ImagePattern(vehicle1));
        this.vehicle2.setFill(new ImagePattern(vehicle2));
        this.vehicle3.setFill(new ImagePattern(vehicle3));
        this.vehicle4.setFill(new ImagePattern(vehicle4));

        Image employee1 = new Image(getClass().getResourceAsStream("/driver/humen5.jpg"));
        Image employee2 = new Image(getClass().getResourceAsStream("/driver/humen3.jpeg"));
        Image employee3 = new Image(getClass().getResourceAsStream("/driver/driver2.jpg"));
        Image employee4 = new Image(getClass().getResourceAsStream("/driver/humen4.jpeg"));

        this.employee1.setFill(new ImagePattern(employee1));
        this.employee2.setFill(new ImagePattern(employee2));
        this.employee3.setFill(new ImagePattern(employee3));
        this.employee4.setFill(new ImagePattern(employee4));


        Image package1 = new Image(getClass().getResourceAsStream("/package/1_0x0_2119x1415_0x520_new_tyres.jpg"));
        Image package2 = new Image(getClass().getResourceAsStream("/package/086924c9-23b7-41c6-9218-45685c563a2e-h.jpeg"));
        Image package3 = new Image(getClass().getResourceAsStream("/package/fast-fashion2.jpeg"));
        Image package4 = new Image(getClass().getResourceAsStream("/package/HealthiestFruits-feb2318dc0a3454993007f57c724753f.jpg"));

        this.package1.setFill(new ImagePattern(package1));
        this.package2.setFill(new ImagePattern(package2));
        this.package3.setFill(new ImagePattern(package3));
        this.package4.setFill(new ImagePattern(package4));

        Image company1 = new Image(getClass().getResourceAsStream("/company/MAS.jpeg"));
        Image company2 = new Image(getClass().getResourceAsStream("/company/LALANNN.jpg"));
        Image company3 = new Image(getClass().getResourceAsStream("/company/brandixleed-002.jpg"));

        this.company1.setFill(new ImagePattern(company1));
        this.company2.setFill(new ImagePattern(company2));
        this.company3.setFill(new ImagePattern(company3));
    }

    public void setDate(){
        LocalDate now = LocalDate.now();
        lblDatePicker.setText(String.valueOf(now));
    }

    public void btnBackToLoginOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/loginForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.parent.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Page");
        stage.centerOnScreen();
    }

    public void btnMoveToDriverFormOnAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/view/driverForm.fxml"));
        DriverFormController driverFormController = new DriverFormController();
        driverFormController.sendParent(this.parent);
        btnDashboard.setTextFill(Paint.valueOf("#979696"));
        btnVehicle.setTextFill(Paint.valueOf("#979696"));
        btnPackage.setTextFill(Paint.valueOf("#979696"));
        btnService.setTextFill(Paint.valueOf("#979696"));
        btnCompany.setTextFill(Paint.valueOf("#979696"));
        btnMap.setTextFill(Paint.valueOf("#979696"));
        btnDriver.setTextFill(Paint.valueOf("#1d4e89"));
        this.rootNode.getChildren().clear();
        this.rootNode.getChildren().add(pane);
    }

    public void btnMoveToVehicleFormAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/view/vehicleForm.fxml"));
        btnDashboard.setTextFill(Paint.valueOf("#979696"));
        btnPackage.setTextFill(Paint.valueOf("#979696"));
        btnService.setTextFill(Paint.valueOf("#979696"));
        btnDriver.setTextFill(Paint.valueOf("#979696"));
        btnCompany.setTextFill(Paint.valueOf("#979696"));
        btnMap.setTextFill(Paint.valueOf("#979696"));
        btnVehicle.setTextFill(Paint.valueOf("#1d4e89"));
        this.rootNode.getChildren().clear();
        this.rootNode.getChildren().add(pane);
    }

    public void btnMoveToPackageFormOnAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/view/packageForm.fxml"));
        btnDashboard.setTextFill(Paint.valueOf("#979696"));
        btnVehicle.setTextFill(Paint.valueOf("#979696"));
        btnService.setTextFill(Paint.valueOf("#979696"));
        btnDriver.setTextFill(Paint.valueOf("#979696"));
        btnCompany.setTextFill(Paint.valueOf("#979696"));
        btnMap.setTextFill(Paint.valueOf("#979696"));
        btnPackage.setTextFill(Paint.valueOf("#1d4e89"));
        this.rootNode.getChildren().clear();
        this.rootNode.getChildren().add(pane);
    }

    public void btnMoveToRouteOnAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/view/routeForm.fxml"));
        btnDashboard.setTextFill(Paint.valueOf("#979696"));
        btnVehicle.setTextFill(Paint.valueOf("#979696"));
        btnPackage.setTextFill(Paint.valueOf("#979696"));
        btnService.setTextFill(Paint.valueOf("#979696"));
        btnDriver.setTextFill(Paint.valueOf("#979696"));
        btnCompany.setTextFill(Paint.valueOf("#979696"));
        this.rootNode.getChildren().clear();
        this.rootNode.getChildren().add(pane);
    }

    public void btnMoveToServiceOnAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/view/ServiceForm.fxml"));
        btnDashboard.setTextFill(Paint.valueOf("#979696"));
        btnVehicle.setTextFill(Paint.valueOf("#979696"));
        btnPackage.setTextFill(Paint.valueOf("#979696"));
        btnDriver.setTextFill(Paint.valueOf("#979696"));
        btnCompany.setTextFill(Paint.valueOf("#979696"));
        btnMap.setTextFill(Paint.valueOf("#979696"));
        btnService.setTextFill(Paint.valueOf("#1d4e89"));
        this.rootNode.getChildren().clear();
        this.rootNode.getChildren().add(pane);
    }

    public void btnMyProfileOnAction(ActionEvent event) {
    }

    public void btnMoveToDashboardOnAction(ActionEvent event) throws IOException {
        BorderPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboardForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.parent.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }

    public void btnMoveToCompanyFormOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/view/companyForm.fxml"));
        btnDashboard.setTextFill(Paint.valueOf("#979696"));
        btnVehicle.setTextFill(Paint.valueOf("#979696"));
        btnPackage.setTextFill(Paint.valueOf("#979696"));
        btnService.setTextFill(Paint.valueOf("#979696"));
        btnDriver.setTextFill(Paint.valueOf("#979696"));
        btnMap.setTextFill(Paint.valueOf("#979696"));
        btnCompany.setTextFill(Paint.valueOf("#1d4e89"));
        this.rootNode.getChildren().clear();
        this.rootNode.getChildren().add(pane);
    }


    public void txtSearchOnAction(ActionEvent actionEvent) {
    }

    public void hplSeeAllPackageOnAction(ActionEvent actionEvent) {
    }

    public void hplTrackNum1OnAction(ActionEvent actionEvent) {
    }

    public void hplTrackNum2OnAction(ActionEvent actionEvent) {
    }

    public void hplTrackNum3OnAction(ActionEvent actionEvent) {
    }

    public void hplSeeAllCompanyOnAction(ActionEvent actionEvent) {
    }

    public void hplCompany1OnAction(ActionEvent actionEvent) {
    }

    public void hplCompany2OnAction(ActionEvent actionEvent) {
    }

    public void hplCompany3OnAction(ActionEvent actionEvent) {
    }

    public void hplTrackNum4OnAction(ActionEvent actionEvent) {
    }

    public void btnMouseClickOnAction(MouseEvent mouseEvent) {
        btnDashboard.setStyle("-fx-background-color: #BDE0FE;\n" +
                "    -fx-border-color: #0F4D87;\n" +
                "    -fx-border-width: 0px 0px 0px 5px;");
        btnDriver.setStyle("-fx-background-color: #BDE0FE;\n" +
                "    -fx-border-color: #0F4D87;\n" +
                "    -fx-border-width: 0px 0px 0px 5px;");
        btnVehicle.setStyle("-fx-background-color: #BDE0FE;\n" +
                "    -fx-border-color: #0F4D87;\n" +
                "    -fx-border-width: 0px 0px 0px 5px;");
        btnCompany.setStyle("-fx-background-color: #BDE0FE;\n" +
                "    -fx-border-color: #0F4D87;\n" +
                "    -fx-border-width: 0px 0px 0px 5px;");
        btnPackage.setStyle("-fx-background-color: #BDE0FE;\n" +
                "    -fx-border-color: #0F4D87;\n" +
                "    -fx-border-width: 0px 0px 0px 5px;");
        btnService.setStyle("-fx-background-color: #BDE0FE;\n" +
                "    -fx-border-color: #0F4D87;\n" +
                "    -fx-border-width: 0px 0px 0px 5px;");

    }

    public void btnLoadMapOnAction(ActionEvent actionEvent) throws IOException {
        btnDashboard.setTextFill(Paint.valueOf("#979696"));
        btnVehicle.setTextFill(Paint.valueOf("#979696"));
        btnPackage.setTextFill(Paint.valueOf("#979696"));
        btnService.setTextFill(Paint.valueOf("#979696"));
        btnDriver.setTextFill(Paint.valueOf("#979696"));
        btnCompany.setTextFill(Paint.valueOf("#979696"));
        btnMap.setTextFill(Paint.valueOf("#1d4e89"));

        StackPane stackPane = FXMLLoader.load(this.getClass().getResource("/view/loadGoogleMap.fxml"));
        Scene scene = new Scene(stackPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Google Map");
        stage.centerOnScreen();

    }

    public void btnInboxOnAction(ActionEvent actionEvent) throws IOException {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load("https://google.com");

        StackPane root = new StackPane();
        root.getChildren().add(webView);

        Scene scene = new Scene(root, 1000, 800);
        Stage ss= new Stage();
        ss.setScene(scene);
        ss.setTitle("Inbox");
        ss.show();
    }

    public void btnSendMailOnAction(ActionEvent actionEvent) throws IOException {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load("https://google.com");

        StackPane root = new StackPane();
        root.getChildren().add(webView);

        Scene scene = new Scene(root, 1000, 800);
        Stage ss= new Stage();
        ss.setScene(scene);
        ss.setTitle("Sent Mail");
        ss.show();
    }
}
