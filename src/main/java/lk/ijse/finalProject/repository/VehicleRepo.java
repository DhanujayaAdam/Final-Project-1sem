package lk.ijse.finalProject.repository;

import lk.ijse.finalProject.DB.Dbconnection;
import lk.ijse.finalProject.model.Vehicle;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static lk.ijse.finalProject.repository.VehicleToBeServiceRepo.getCurrentDistance;

public class VehicleRepo {
    public static boolean registerVehicle(Vehicle vehicle) throws SQLException {
        String sql = "INSERT INTO Vehicle VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1, vehicle.getId());
        pstm.setObject(2, vehicle.getName());
        pstm.setObject(3, vehicle.getVehicle_number());
        pstm.setObject(4, vehicle.getChassis());
        pstm.setObject(5, vehicle.getEngineNum());
        pstm.setObject(6, vehicle.getColor());
        pstm.setObject(7, vehicle.getYom());
        pstm.setObject(8, vehicle.getRegDate());
        pstm.setObject(9, vehicle.getProfile_picture());
        pstm.setObject(10, vehicle.getCurrentDistance());
        return pstm.executeUpdate() > 0;
    }

    public static String getVehicleId() throws SQLException {
        String sql = "SELECT vehicle_id FROM Vehicle ORDER BY vehicle_id DESC LIMIT 1";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String dbId = resultSet.getString("vehicle_id");
            return dbId;
        }
        return null;
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

    public static List<String> getAllVehicleId() throws SQLException {
        String sql = "SELECT v.vehicle_id\n" +
                "FROM Vehicle v\n" +
                "LEFT JOIN Driver d ON v.vehicle_id = d.vehicle_id\n" +
                "WHERE d.vehicle_id IS NULL;\n";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }

    public static List<String> getId() throws SQLException {
        String sql = "SELECT vehicle_id FROM Vehicle";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString("vehicle_id");
            idList.add(id);
            System.out.println(id);
        }
        return idList;
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

    public static Vehicle getDetail(String id) throws SQLException {
        String sql = "SELECT * FROM Vehicle WHERE vehicle_number = ?";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,id);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            String vehiId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String number = resultSet.getString(3);
            String chassis = resultSet.getString(4);
            String engine = resultSet.getString(5);
            String color = resultSet.getString(6);
            String yom = resultSet.getString(7);
            Date regDate = Date.valueOf(resultSet.getString(8));
            double distance = resultSet.getDouble(10);
            String img = resultSet.getString(9);
            Vehicle vehicle = new Vehicle(vehiId,name,number,chassis,engine,color,yom,regDate,distance,img);
            return vehicle;
        }
        return null;
    }

    public static List<String > getVehicleNumber() throws SQLException {
        String sql = "SELECT vehicle_number FROM Vehicle ORDER BY vehicle_id DESC LIMIT 7";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        List<String> numList = new ArrayList<>();
        while (resultSet.next()){
            String number = resultSet.getString("vehicle_number");
            numList.add(number);
        }
        return numList;
    }

    public static List<String> getVehicleName() throws SQLException {
        String sql = "SELECT brand_name FROM Vehicle ORDER BY vehicle_id DESC LIMIT 7";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        List<String> nameList = new ArrayList<>();
        while (resultSet.next()){
            String number = resultSet.getString("brand_name");
            nameList.add(number);
        }
        return nameList;
    }

    public static boolean deleteVehicle(String numberPlate) throws SQLException {
        String sql = "DELETE FROM Vehicle WHERE vehicle_number = ?";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,numberPlate);
        return pstm.executeUpdate() > 0;
    }

    public static boolean updateCurrentDistance(double destination, String vehicleId) throws SQLException {
        String sql = "UPDATE Vehicle SET current_distance = current_distance + ? WHERE vehicle_id = ?";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,destination);
        pstm.setObject(2,vehicleId);
        return pstm.executeUpdate() > 0;
    }
}
