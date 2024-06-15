package lk.ijse.finalProject.repository;

import lk.ijse.finalProject.DB.Dbconnection;
import lk.ijse.finalProject.model.VehicleToBeService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleToBeServiceRepo {

    public static boolean addReport(VehicleToBeService vehicleService) throws SQLException {
        String sql = "INSERT INTO Vehicle_to_be_serviced VALUES(?,?,?,?)";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,vehicleService.getId());
        pstm.setObject(2,vehicleService.getVehicleId());
        pstm.setObject(3,vehicleService.getStatus());
        pstm.setObject(4,vehicleService.getAlert_distance());
        return pstm.executeUpdate() > 0;
    }
    public static boolean update(String vehicleId, double destination) throws SQLException {
        String sql = "UPDATE Vehicle_to_be_serviced SET alert_distance = alert_distance + ? WHERE vehicle_id = ?";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,destination);
        pstm.setObject(2,vehicleId);
        return pstm.executeUpdate() > 0;
    }

    public static double getCurrentDistance(String vehicleId) throws SQLException {
        String sql = "SELECT alert_distance FROM Vehicle_to_be_serviced WHERE vehicle_id = ?";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,vehicleId);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return resultSet.getDouble("alert_distance");
        }
        return 0;
    }

    public static boolean clearDistance(String vehicleId) throws SQLException {
        String sql = "UPDATE Vehicle_to_be_serviced SET alert_distance = ? WHERE vehicle_id = ?";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,0.0);
        pstm.setObject(2,vehicleId);
        return pstm.executeUpdate() > 0;
    }
}
