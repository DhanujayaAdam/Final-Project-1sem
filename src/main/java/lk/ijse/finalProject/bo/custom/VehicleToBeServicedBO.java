package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dto.VehicleToBeServiceDTO;

import java.sql.SQLException;
import java.util.List;

public interface VehicleToBeServicedBO extends SuperBO {
    public boolean addReport(VehicleToBeServiceDTO obj) throws SQLException;
    public boolean updateReport(VehicleToBeServiceDTO obj) throws SQLException;
    public boolean deleteReport(String id) throws SQLException;
    public String getReportId() throws SQLException;
    public String generateNewId(String id);
    public VehicleToBeServiceDTO getReportObject(String id) throws SQLException;
    public List<VehicleToBeServiceDTO> getAllReport() throws SQLException;
    public double getCurrentDistance(String vehicleId) throws SQLException;
    public boolean clearDistance(String vehicleId) throws SQLException;
    public boolean updateCurrentDistance(double distance, String vehicleId) throws SQLException;
}
