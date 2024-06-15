package lk.ijse.finalProject.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    public static boolean setTextFieldColor(TextField location, javafx.scene.control.TextField textField) {
        if (Regex.isTextFieldValid(location,textField.getText())){
            textField.setStyle("-fx-text-fill: green");
            textField.setStyle("-fx-text-fill: green");

            return true;
        } else {
            textField.setStyle("-fx-text-fill: Red");
            textField.setStyle("-fx-text-fill: Red");
            return false;
        }
    }

    private static boolean isTextFieldValid(TextField location, String text) {
        String field = "";
        switch (location) {
            case NAME:
                field = "^[A-z|\\\\s]{3,}$";
                break;
            case PASSWORD:
                field = "[A-z][0-9]{8,}$";
                break;
            case WORD:
                field = "^[A-Za-z\\s]{4,}$";
                break;
            case DATE:
                field = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
                break;
            case PRICE:
                field = "^-?\\d*\\.\\d+$";
                break;
            case WEIGHT:
                field = "^-?\\d*\\.\\d+$";
                break;
            case DISTANCE:
                field = "^-?\\d*\\.\\d+$";
                break;
            case PHONE:
                field ="^([+]94{1,3}|[0])([1-9]{2})([0-9]){7}$";
                break;
            case EMAIL:
                field = "^([A-z])([A-z0-9.]){1,}[@]([A-z0-9]){1,10}[.]([A-z]){2,5}$";
                break;
            case NIC:
                field = "^\\d{11,12}[a-z]?$";
                break;
            case YOM:
                field = "^\\d{4}$";
                break;
            case NUMBERPLATE:
                field = "^[A-Z]{1,3}-\\d{1,4}$";
                break;
            case MIX:
                field = "^[a-zA-Z0-9]{17}$";

        }
        Pattern pattern = Pattern.compile(field);
        if (text != null){
            if (text.trim().isEmpty()){
                return false;
            }
        }else {
            return false;
        }

        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()){
            return true;
        }
        return false;
    }
}
