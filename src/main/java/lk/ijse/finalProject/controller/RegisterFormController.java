package lk.ijse.finalProject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.finalProject.bo.BOFactory;
import lk.ijse.finalProject.bo.custom.UserBO;
import lk.ijse.finalProject.bo.custom.impl.UserBOImpl;
import lk.ijse.finalProject.dto.UserDTO;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterFormController {
    @FXML
    public TextField txtUsername;
    @FXML
    public TextField txtPassword;
    @FXML
    public TextField txtReEnterPassword;
    @FXML
    public AnchorPane pane;
    @FXML
    public Label lbluser;
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getInstance(BOFactory.BoType.USER);

    public void txtUsernameOnAction(ActionEvent actionEvent) {
        String user = txtUsername.getText();
        lbluser.setText(user);
        txtPassword.getOnAction();
    }

    public void txtPasswordOnAction(ActionEvent actionEvent) {
        txtReEnterPassword.getOnAction();
    }

    public void txtReEnterPasswordOnAction(ActionEvent actionEvent) {
        btnRegisterOnAction(actionEvent);
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        String userName = txtUsername.getText();
        String password = txtPassword.getText();
        String rePw = txtReEnterPassword.getText();

        try {
            String currentId = userBO.getUserId();
            String nextId = null;
            if (currentId != null){
                String[] split = currentId.split("U00");
                int idNum = Integer.parseInt(split[1]);
                nextId = "U00" + ++idNum;
                registerUser(userName,password,rePw,nextId);
            } else {
                nextId = "U001";
                registerUser(userName, password, rePw, nextId);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    private void registerUser(String userName, String password, String rePw, String currentId) throws SQLException {
        if (password.equals(rePw)) {
            boolean isSaved = userBO.addUser(new UserDTO(currentId, userName, password, rePw));
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "User saved successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "User saved unsuccessfully").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR,"Please check the password").show();
        }
    }
    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/loginForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
        stage.show();
    }
}
