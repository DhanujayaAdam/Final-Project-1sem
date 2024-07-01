package lk.ijse.finalProject.dao.custom.impl;

import com.fasterxml.jackson.databind.ext.SqlBlobSerializer;
import lk.ijse.finalProject.dao.SqlUtil;
import lk.ijse.finalProject.dao.custom.VehicleDAO;
import lk.ijse.finalProject.db.Dbconnection;
import lk.ijse.finalProject.entity.Vehicle;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAOImpl implements VehicleDAO {
    @Override
    public List<String> getVehicleId() throws SQLException {
        ResultSet rst = SqlUtil.execute("SELECT v.vehicle_id FROM Vehicle v LEFT JOIN Driver d ON v.vehicle_id = d.vehicle_id WHERE d.vehicle_id IS NULL;");
        ArrayList<String> idList = new ArrayList<>();
        while (rst.next()){
            idList.add(rst.getString("vehicle_id"));
        }
        return idList;
    }

    @Override
    public List<String> getCurrentVehicleList() throws SQLException {
        ResultSet rst = SqlUtil.execute("SELECT vehicle_id FROM Vehicle");
        ArrayList<String> idList = new ArrayList<>();
        while(rst.next()){
            idList.add(rst.getString("vehicle_id"));
        }
        return  idList;
    }

    @Override
    public List<String> getVehicleNameList() throws SQLException {
        ResultSet rst = SqlUtil.execute("SELECT brand_name FROM Vehicle ORDER BY vehicle_id DESC LIMIT 7");
        ArrayList<String> nameList = new ArrayList<>();
        while (rst.next()){
            nameList.add(rst.getString("brand_name"));
        }
        return nameList;
    }

    public static String getVehicleId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("V");
            int idNum = Integer.parseInt(split[1]);
            return "V" + ++idNum;
        } else {
            return "V1";
        }
    }
    @Override
    public boolean add(Vehicle obj) throws SQLException {
        System.out.println(obj);
        return SqlUtil.execute("INSERT INTO Vehicle VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",obj.getId(),obj.getName(),obj.getVehicle_number(),obj.getChassis(),obj.getEngineNum(),obj.getColor(),obj.getYom(),obj.getRegDate(),obj.getProfile_picture(),obj.getCurrentDistance());
    }

    @Override
    public boolean update(Vehicle obj) throws SQLException {
        return SqlUtil.execute("UPDATE Vehicle SET brand_name = ?,vehicle_number = ?,chassis_number = ?,engine_number = ?,color = ?,yom = ?,registration_date = ?,current_distance = ?,profile_picture = ? WHERE vehicle_id = ?",obj.getName(),obj.getVehicle_number(),obj.getChassis(),obj.getEngineNum(),obj.getColor(),obj.getYom(),obj.getRegDate(),obj.getCurrentDistance(),obj.getProfile_picture(),obj.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SqlUtil.execute("DELETE FROM Vehicle WHERE vehicle_number = ?",id);
    }

    @Override
    public String getId() throws SQLException {
        ResultSet rst = SqlUtil.execute("SELECT vehicle_id FROM Vehicle ORDER BY vehicle_id DESC LIMIT 1");
        rst.next();
        return  rst.getString("vehicle_id");
    }
    @Override
    public String generateNewId(String id) {
        return null;
    }

    @Override
    public Vehicle getObject(String value) throws SQLException {
        if (!value.startsWith("V")) {
            ResultSet resultSet = SqlUtil.execute("SELECT * FROM Vehicle WHERE vehicle_number = ?", value);
            resultSet.next();
            return new Vehicle(resultSet.getString("vehicle_id"), resultSet.getString("brand_name"), resultSet.getString("vehicle_number"), resultSet.getString("chassis_number"), resultSet.getString("engine_number"), resultSet.getString("color"), resultSet.getString("yom"), resultSet.getDate("registration_date"), resultSet.getDouble("current_distance"), resultSet.getString("profile_picture"));
        }
        ResultSet resultSet = SqlUtil.execute("SELECT * FROM Vehicle WHERE vehicle_id = ?",value);
        resultSet.next();
        return new Vehicle(resultSet.getString("vehicle_id"), resultSet.getString("brand_name"), resultSet.getString("vehicle_number"), resultSet.getString("chassis_number"), resultSet.getString("engine_number"), resultSet.getString("color"), resultSet.getString("yom"), resultSet.getDate("registration_date"), resultSet.getDouble("current_distance"), resultSet.getString("profile_picture"));
    }

    @Override
    public List<Vehicle> getAll() throws SQLException {
        return null;
    }

    public static Vehicle getValues(String vehiId) throws SQLException {
        String sql = "SELECT * FROM Vehicle WHERE vehicle_id = ?";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1, vehiId);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String number = resultSet.getString(3);
            String chassis = resultSet.getString(4);
            String engine = resultSet.getString(5);
            String color = resultSet.getString(6);
            String yom = resultSet.getString(7);
            Date reg = Date.valueOf(resultSet.getString(8));
            double distance = resultSet.getDouble(10);
            Blob profile = resultSet.getBlob(9);
            Vehicle vehicle = new Vehicle(id, name, number, chassis, engine, color, yom, reg, distance, profile.toString());
            return vehicle;
        }
        return null;
    }

    public static boolean updateVehicle(Vehicle vehicle) throws SQLException {
        String sql = "UPDATE Vehicle SET vehicle_id = ?,brand_name = ?,vehicle_number = ?,chassis_number = ?,engine_number = ?,color = ?,yom = ?,registration_date = ?,current_distance = ?,profile_picture = ? WHERE vehicle_id = ?";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,vehicle.getId());
        pstm.setObject(2,vehicle.getName());
        pstm.setObject(3,vehicle.getVehicle_number());
        pstm.setObject(4,vehicle.getChassis());
        pstm.setObject(5,vehicle.getEngineNum());
        pstm.setObject(6,vehicle.getColor());
        pstm.setObject(7,vehicle.getYom());
        pstm.setObject(8,vehicle.getRegDate());
        pstm.setObject(10,vehicle.getCurrentDistance());
        pstm.setObject(9, vehicle.getProfile_picture());
        pstm.setObject(11,vehicle.getId());
        return pstm.executeUpdate() > 0;
    }
    @Override
    public List<String> getVehicleNumber() throws SQLException {
        ResultSet resultSet = SqlUtil.execute("SELECT vehicle_number FROM Vehicle ORDER BY vehicle_id DESC LIMIT 7");
        List<String> numList = new ArrayList<>();
        while (resultSet.next()){
            numList.add(resultSet.getString("vehicle_number"));
        }
        return numList;
    }
    public static boolean updateCurrentDistance(double destination, String vehicleId) throws SQLException {
        String sql = "UPDATE Vehicle SET current_distance = current_distance + ? WHERE vehicle_id = ?";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,destination);
        pstm.setObject(2,vehicleId);
        return pstm.executeUpdate() > 0;
    }
}
