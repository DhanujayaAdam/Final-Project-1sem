package lk.ijse.finalProject.controller;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoadGoogleMapFormController implements Initializable {
    public StackPane root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadWorldMap();
    }

    private void loadWorldMap() {
        System.out.println("click");

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load("https://www.google.com/url?sa=t&source=web&rct=j&opi=89978449&url=https://maps.google.com/&ved=2ahUKEwinjty624-GAxVEzjgGHVx6DDMQFnoECA0QAQ&usg=AOvVaw3z2z03MnvIwD2K6kwtdD9z");

        StackPane root = new StackPane();
        root.getChildren().add(webView);

        Scene scene = new Scene(root, 1000, 800);
        Stage ss= new Stage();
        ss.setScene(scene);
        ss.setTitle("Google Map");
        ss.show();
    }
}
