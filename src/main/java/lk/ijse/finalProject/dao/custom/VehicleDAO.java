package lk.ijse.finalProject.dao.custom;

import lk.ijse.finalProject.dao.CrudDAO;
import lk.ijse.finalProject.entity.Vehicle;

import java.sql.SQLException;
import java.util.List;

public interface VehicleDAO extends CrudDAO<Vehicle> {
    public List<String> getVehicleId() throws SQLException;
    public List<String> getCurrentVehicleList() throws SQLException;
    public List<String> getVehicleNameList() throws SQLException;
    public List<String> getVehicleNumber() throws  SQLException;
    public boolean updateVehicleDistance(String vehicleId, double distance) throws SQLException;
    public double getCurrentDistance(String vehicleId) throws SQLException;
}
