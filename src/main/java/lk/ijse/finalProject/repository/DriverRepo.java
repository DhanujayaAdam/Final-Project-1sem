package lk.ijse.finalProject.repository;

import javafx.collections.FXCollections;
import javafx.scene.LightBase;
import javafx.scene.control.ComboBox;
import javafx.scene.shape.Circle;
import lk.ijse.finalProject.DB.Dbconnection;
import lk.ijse.finalProject.model.Driver;
import lk.ijse.finalProject.model.Vehicle;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverRepo {
    public static boolean saveDriver(String driverId, String firstName, String lastName, String address, String nic, Date dob, String vehicleId, String phone, String email, String profile) throws SQLException {
        String sql = "INSERT INTO Driver VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,driverId);
        pstm.setObject(2,firstName);
        pstm.setObject(3,lastName);
        pstm.setObject(4,address);
        pstm.setObject(5,nic);
        pstm.setObject(6,dob);
        pstm.setObject(7,vehicleId);
        pstm.setObject(8,phone);
        pstm.setObject(9,email);
        pstm.setObject(10,profile);
        return pstm.executeUpdate() > 0;
    }

    public static String generateDriverId(String currentDriverId) {
        if (currentDriverId != null) {
            String[] split = currentDriverId.split("D");
            int idNum = Integer.parseInt(split[1]);
            return "D" + ++idNum;
        } else {
            return "D1";
        }
    }

    public static String getCurrentDriverId() throws SQLException {
        String sql = "SELECT driver_id FROM Driver ORDER BY driver_id DESC LIMIT 1";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            String dbDriverId = resultSet.getString("driver_id");
            return dbDriverId;
        }
        return null;
    }

    public static void setValues() throws SQLException {
        String sql = "SELECT * FROM Driver";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.executeQuery();
    }

    public static List<String> setComboBox() throws SQLException {
        String sql = "SELECT driver_id FROM Driver";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        List<String > idList = new ArrayList<>();
        while (resultSet.next()){
            String driverId = resultSet.getString("driver_id");
            idList.add(driverId);
        }
        return idList;
    }

    public static List<String> getName() throws SQLException {
        String sql = "SELECT first_name,second_name FROM Driver ORDER BY driver_id DESC LIMIT 8";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        List<String> nameList = new ArrayList<>();
        while (resultSet.next()) {
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("second_name");
            nameList.add(firstName + " " + lastName);
        }
        return nameList;
    }

    public static Driver getDriver(String comboValue) throws SQLException {
        String sql = "SELECT * FROM Driver WHERE driver_id = ?";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,comboValue);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            String id = resultSet.getString(1);
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            String address = resultSet.getString(4);
            String nic = resultSet.getString(5);
            String dob = resultSet.getString(6);
            String vehicleId = resultSet.getString(7);
            String tel = resultSet.getString(8);
            String email = resultSet.getString(9);
            String dp = resultSet.getString(10);
            lk.ijse.finalProject.model.Driver driver = new lk.ijse.finalProject.model.Driver(id,firstName,lastName,address,nic,dob,vehicleId,tel,email,dp);
            return driver;
        }
        return null;
    }

    public static boolean updateDriver(Driver driver) throws SQLException {
        String sql = "UPDATE Driver SET driver_id = ?,first_name = ?,second_name = ?,address = ?,date_of_birth = ?,NIC_number = ?,contact_number = ?,email = ?,profile_picture = ? WHERE driver_id = ?";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,driver.getDriver_id());
        pstm.setObject(2,driver.getFirstName());
        pstm.setObject(3,driver.getLastname());
        pstm.setObject(4,driver.getAddress());
        pstm.setObject(5,driver.getDob());
        pstm.setObject(6,driver.getNic());
        pstm.setObject(7,driver.getContact());
        pstm.setObject(8,driver.getEmail());
        pstm.setObject(9,driver.getPic());
        pstm.setObject(10,driver.getDriver_id());
        return pstm.executeUpdate() > 0;
    }

    public static List<String> getId() throws SQLException {
        String sql ="SELECT NIC_number FROM Driver ORDER BY driver_id DESC LIMIT 8";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        List<String> idList = new ArrayList<>();
        while (resultSet.next()){
            String nic = resultSet.getString(1);
            idList.add(nic);
        }
        return idList;
    }

    public static Driver getDetail(String id) throws SQLException {
        String sql = "SELECT * FROM Driver WHERE NIC_number = ?";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,id);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            String driverId = resultSet.getString(1);
            String firstName = resultSet.getString(2);
            String secondName = resultSet.getString(3);
            String address = resultSet.getString(4);
            String nic = resultSet.getString(5);
            String dob = resultSet.getString(6);
            String vehiId = resultSet.getString(7);
            String tel = resultSet.getString(8);
            String email = resultSet.getString(9);
            String pic = resultSet.getString(10);
            Driver driver = new Driver(driverId,firstName,secondName,address,nic,dob,vehiId,tel,email,pic);
            return driver;
        }
        return null;
    }

    public static Driver getAll(String name) throws SQLException {
        String sql = "SELECT * FROM Driver WHERE first_name = ?";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,name);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            String driverId = resultSet.getString(1);
            String firstName = resultSet.getString(2);
            String secondName = resultSet.getString(3);
            String address = resultSet.getString(4);
            String nic = resultSet.getString(5);
            String dob = resultSet.getString(6);
            String vehiId = resultSet.getString(7);
            String tel = resultSet.getString(8);
            String email = resultSet.getString(9);
            String pic = resultSet.getString(10);
            Driver driver = new Driver(driverId,firstName,secondName,address,nic,dob,vehiId,tel,email,pic);
            return driver;
        }
        return null;
    }

    public static boolean deleteDriver(String firstName) throws SQLException {
        String sql = "DELETE FROM Driver WHERE first_name = ?";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,firstName);
        return pstm.executeUpdate() > 0;
    }
}



