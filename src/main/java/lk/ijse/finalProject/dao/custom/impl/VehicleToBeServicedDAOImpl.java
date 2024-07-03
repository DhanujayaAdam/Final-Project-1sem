package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.SqlUtil;
import lk.ijse.finalProject.dao.custom.VehicleDAO;
import lk.ijse.finalProject.dao.custom.VehicleToBeServicedDAO;
import lk.ijse.finalProject.db.Dbconnection;
import lk.ijse.finalProject.entity.VehicleToBeService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VehicleToBeServicedDAOImpl implements VehicleToBeServicedDAO {
    @Override
    public double getCurrentDistance(String vehicleId) throws SQLException {
        String sql = "SELECT alert_distance FROM Vehicle_to_be_serviced WHERE vehicle_id = ?";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,vehicleId);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return resultSet.getDouble("alert_distance");
        }
        return 0;
    }
    @Override
    public boolean clearDistance(String vehicleId) throws SQLException {
        return SqlUtil.execute("UPDATE Vehicle_to_be_serviced SET alert_distance = ? WHERE vehicle_id = ?",0.0,vehicleId);
    }

    @Override
    public boolean updateCurrentDistance(double distance,String vehicleId) throws SQLException {
        return SqlUtil.execute("UPDATE Vehicle_to_be_serviced SET alert_distance = alert_distance + ? WHERE vehicle_id = ?",distance,vehicleId);
    }

    @Override
    public boolean add(VehicleToBeService obj) throws SQLException {
        return SqlUtil.execute("INSERT INTO Vehicle_to_be_serviced VALUES(?,?,?,?)",obj.getId(),obj.getVehicleId(),obj.getStatus(),obj.getAlert_distance());
    }

    @Override
    public boolean update(VehicleToBeService obj) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public String getId() throws SQLException {
        return null;
    }

    @Override
    public String generateNewId(String id) {
        return null;
    }

    @Override
    public VehicleToBeService getObject(String id) throws SQLException {
        return null;
    }

    @Override
    public List<VehicleToBeService> getAll() throws SQLException {
        return null;
    }
}
