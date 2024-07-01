package lk.ijse.finalProject.dao.custom;

import lk.ijse.finalProject.dao.CrudDAO;
import lk.ijse.finalProject.entity.VehicleToBeService;

import java.sql.SQLException;

public interface VehicleToBeServicedDAO extends CrudDAO<VehicleToBeService> {
    public double getCurrentDistance(String vehicleId) throws SQLException;
    public boolean clearDistance(String vehicleId) throws SQLException;
}
