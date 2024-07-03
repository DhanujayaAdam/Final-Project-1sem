package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.SqlUtil;
import lk.ijse.finalProject.dao.custom.DriverDAO;
import lk.ijse.finalProject.db.Dbconnection;
import lk.ijse.finalProject.entity.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverDAOImpl implements DriverDAO {
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
        if(resultSet.next())
            return resultSet.getString("driver_id");
        return null;
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
        } else if (value.startsWith("V")){
            ResultSet rst = SqlUtil.execute("SELECT * FROM Driver WHERE vehicle_id = ?", value);
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



