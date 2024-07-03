package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dao.SuperDAO;
import lk.ijse.finalProject.dto.VehicleDTO;

import java.sql.SQLException;
import java.util.List;

public interface VehicleBO extends SuperBO {
    public boolean saveVehicle(VehicleDTO vehicle) throws SQLException;
    public boolean updateVehicle(VehicleDTO vehicle) throws SQLException;
    public boolean deleteVehicle(String id) throws  SQLException;
    public String getVehicleId() throws SQLException;
    public VehicleDTO getVehicleObject(String id) throws SQLException;
    public List<VehicleDTO> getAll() throws SQLException;
    public List<String> vehicleIdList() throws SQLException;
    public List<String> getCurrentVehicleList() throws SQLException;
    public List<String> getVehicleNameList() throws SQLException;
    public List<String> getVehicleNumber() throws SQLException;
    public boolean updateVehicleDistance(String vehicleId, double distance) throws SQLException;
    public double getCurrentDistance(String vehicleId) throws SQLException;
}
