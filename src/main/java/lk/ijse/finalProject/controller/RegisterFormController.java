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
import lk.ijse.finalProject.DB.Dbconnection;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            String currentId = getCurrentId();
            String nextId =getNextAvailableId(currentId);
            registerUser(userName,password,rePw,nextId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void registerUser(String userName, String password, String rePw, String currentId) throws SQLException {
        String pw = txtPassword.getText();
        if (password.equals(rePw) && pw != "") {
            String sql = "INSERT INTO User VALUES (?, ?, ?)";
            PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setObject(1, currentId);
            pstm.setObject(2, userName);
            pstm.setObject(3, password);
            pstm.executeUpdate();
            new Alert(Alert.AlertType.CONFIRMATION, "User saved successfully").show();
        } else if (pw.equals("")) {
            new Alert(Alert.AlertType.INFORMATION, "Please set up a password").show();
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Please check your password").show();
        }
    }

    private String getNextAvailableId(String currentId) {
        String[] split = currentId.split("U00");
        int idNum = Integer.parseInt(split[1]);
        return "U00" + ++idNum;
    }

    private String getCurrentId() throws SQLException {
        String sql = "SELECT user_id FROM User ORDER BY user_id DESC LIMIT 1";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            String dbUser = resultSet.getString("user_id");
            return dbUser;
        } else {
            return "U001";
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
