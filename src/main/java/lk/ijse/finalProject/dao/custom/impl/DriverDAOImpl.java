package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.SqlUtil;
import lk.ijse.finalProject.dao.custom.DriverDAO;
import lk.ijse.finalProject.db.Dbconnection;
import lk.ijse.finalProject.entity.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverDAOImpl implements DriverDAO {

    public static void setValues() throws SQLException {
        String sql = "SELECT * FROM Driver";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.executeQuery();
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

    @Override
    public boolean add(Driver obj) throws SQLException {
        return SqlUtil.execute("INSERT INTO Driver VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",obj.getDriver_id(),obj.getFirstName(),obj.getLastname(),obj.getAddress(),obj.getNic(),obj.getDob(),obj.getVehicle_id(),obj.getContact(),obj.getEmail(),obj.getPic());
    }

    @Override
    public boolean update(Driver obj) throws SQLException {
        return SqlUtil.execute("UPDATE Driver SET first_name = ?,second_name = ?,address = ?,date_of_birth = ?,NIC_number = ?,contact_number = ?,email = ?,profile_picture = ? WHERE driver_id = ?",obj.getFirstName(),obj.getLastname(),obj.getAddress(),obj.getDob(),obj.getNic(),obj.getContact(),obj.getEmail(),obj.getPic(),obj.getDriver_id());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SqlUtil.execute("DELETE FROM Driver WHERE first_name = ?",id);
    }

    @Override
    public String getId() throws SQLException {
        ResultSet resultSet = SqlUtil.execute("SELECT driver_id FROM Driver ORDER BY driver_id DESC LIMIT 1");
        resultSet.next();
        return resultSet.getString("driver_id");
    }
    @Override
    public String generateNewId(String id) {
        return null;
    }

    @Override
    public Driver getObject(String value) throws SQLException {
        if (value.startsWith("D")) {
            ResultSet rst = SqlUtil.execute("SELECT * FROM Driver WHERE driver_id = ?", value);
            rst.next();
            Driver driver = new Driver(rst.getString("driver_id"), rst.getString("first_name"), rst.getString("second_name"), rst.getString("address"), rst.getString("NIC_number"), rst.getDate("date_of_birth"), rst.getString("vehicle_id"), rst.getString("contact_number"), rst.getString("email"), rst.getString("profile_picture"));
            return driver;
        }
        ResultSet rst = SqlUtil.execute("SELECT * FROM Driver WHERE first_name = ?", value);
        rst.next();
        Driver driver = new Driver(rst.getString("driver_id"), rst.getString("first_name"), rst.getString("second_name"), rst.getString("address"), rst.getString("NIC_number"), rst.getDate("date_of_birth"), rst.getString("vehicle_id"), rst.getString("contact_number"), rst.getString("email"), rst.getString("profile_picture"));
        return driver;
    }

    @Override
    public List<Driver> getAll() throws SQLException {
        return null;
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
            Date dob = Date.valueOf(resultSet.getString(6));
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
            Date dob = Date.valueOf(resultSet.getString(6));
            String vehiId = resultSet.getString(7);
            String tel = resultSet.getString(8);
            String email = resultSet.getString(9);
            String pic = resultSet.getString(10);
            Driver driver = new Driver(driverId,firstName,secondName,address,nic,dob,vehiId,tel,email,pic);
            return driver;
        }
        return null;
    }
    @Override
    public List<String> driverId() throws SQLException {
        ResultSet resultSet = SqlUtil.execute("SELECT driver_id FROM Driver");
        ArrayList<String> ids = new ArrayList<>();
        while (resultSet.next()){
            ids.add(resultSet.getString("driver_id"));
        }
        return ids;
    }

    @Override
    public List<String> getDriverName() throws SQLException {
        ResultSet rst = SqlUtil.execute("SELECT first_name,second_name FROM Driver ORDER BY driver_id DESC LIMIT 8");
        ArrayList<String> nameList = new ArrayList<>();
        while(rst.next()){
            nameList.add(rst.getString("first_name")+" "+rst.getString("second_name"));
        }
        return nameList;
    }
}



