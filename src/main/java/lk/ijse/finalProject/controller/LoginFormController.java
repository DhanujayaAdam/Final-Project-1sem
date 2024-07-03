package lk.ijse.finalProject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lk.ijse.finalProject.bo.BOFactory;
import lk.ijse.finalProject.bo.custom.UserBO;
import lk.ijse.finalProject.bo.custom.impl.UserBOImpl;
import lk.ijse.finalProject.util.Regex;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoginFormController {
    @FXML
    public TextField txtUsername;
    @FXML
    public TextField txtPassword;
    public AnchorPane rootNode;
    public TextField txtVisiblePassword;
    public Button btnInvisible;
    public ImageView imvBlind;
    public ImageView imvEye;
    public JFXButton btnLogin;
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getInstance(BOFactory.BoType.USER);

    public void initialize(){}


    public void txtUsernameOnAction(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

    public void txtPasswordOnAction(ActionEvent event) {
        btnLoginOnAction(event);
    }

    public void btnLoginOnAction(ActionEvent actionEvent) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
      //  if (isValided()) {
            try {
                checkCredintial(username, password);
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
       // }
    }

    private boolean isValided() {
        if (!Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.NAME, txtUsername)) return false;
        if (!Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.PASSWORD, txtPassword)) return false;
        return true;
    }

    private void checkCredintial(String username, String password) throws SQLException {

        List<String> list = userBO.checkCredintial(username, password);
        if (username.equals(list.get(0))){
            if (password.equals(list.get(1))){
                try {
                    navigateToTheDashboard(username);
                } catch (IOException e) {
                    new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR,"Invalid password").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR,"Sorry can't find user").show();
        }
    }

    private void navigateToTheDashboard(String dbUser) throws IOException {
        BorderPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboardForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("US CARGO FREIGHT SERVICE");
        stage.centerOnScreen();
        PackageFormController packageFormController = new PackageFormController();
        packageFormController.sendUser(dbUser);
    }

    public void hplForgotPasswordOnAction(ActionEvent actionEvent) {
    }

    public void hplRegisterOnAction(ActionEvent actionEvent) throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/view/registerForm.fxml"));
        Scene scene = new Scene(node);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Registration Form");
        stage.centerOnScreen();
        stage.show();

    }

    public void btnVisibleTxtOnAction(ActionEvent actionEvent) {
        txtVisiblePassword.setText(txtPassword.getText());
        txtVisiblePassword.setVisible(true);
        txtVisiblePassword.insertText(0,"");
        btnInvisible.setVisible(true);
        imvBlind.setVisible(true);

    }

    public void btnVisiblePasswordTxtOnAction(ActionEvent actionEvent) {
        txtVisiblePassword.setVisible(false);
        btnInvisible.setVisible(false);
        imvBlind.setVisible(false);
        imvEye.setVisible(true);

    }

    public void txtPasswordKeyReleasedOnAction(KeyEvent keyEvent) {
       // Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.PASSWORD,txtPassword);
    }

    public void txtUserNameKeyReleasedOnAction(KeyEvent keyEvent) {
       // Regex.setTextFieldColor(lk.ijse.finalProject.util.TextField.NAME,txtUsername);
    }
}
