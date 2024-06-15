package lk.ijse.finalProject.controller;

import com.google.protobuf.StringValue;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import lk.ijse.finalProject.DB.Dbconnection;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ViewEmployeeFormController extends Application implements Initializable {

    public Label lblImage;
    public AnchorPane root;
    public ImageView image;
    public Circle profile;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/view/viewEmployee.fxml"));
        Scene scene = new Scene(pane);
        stage.setTitle("View Image");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        Label label = new Label();
        label.setPrefSize(50,50);
        label.setLabelFor(pane);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image(getClass().getResourceAsStream("/driver/humen3.jpeg"));
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,new BackgroundSize(10,10,true,true,true,true));
        Background background = new Background(backgroundImage);
        lblImage.setBackground(background);
       String path = "/home/dhanujaya/Desktop/FinalProject/Final-Project/src/main/resources/getImage/image.jpeg";
        String sql = "SELECT profile_picture FROM Driver WHERE driver_id = ?";
        try {
            PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setObject(1,"D1");
            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()){
                byte[] bytes = resultSet.getBytes("profile_picture");
                ImageIcon imageIcon = new ImageIcon(bytes);
                FileOutputStream fos = new FileOutputStream(path);
                fos.write(bytes);
//                Image image1 = new Image(Arrays.toString(bytes));
//                profile.setFill(new ImagePattern(image1));

//                System.out.println(bytea);
//                FileOutputStream fos = new FileOutputStream(path);
//                System.out.println(fos);
//                System.out.println("retrive image");
//                fos.write(bytea);
///               Image image = new Image(Arrays.toString(bytea));
//                System.out.println(image);
//                BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,new BackgroundSize(10,10,true,true,true,true));
//                Background background = new Background(backgroundImage);
//                lblImage.setBackground(background);
//                System.out.println("Show image");

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (FileNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
