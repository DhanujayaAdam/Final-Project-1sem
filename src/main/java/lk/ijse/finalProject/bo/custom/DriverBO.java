package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dao.SuperDAO;
import lk.ijse.finalProject.dto.DriverDTO;

import java.sql.SQLException;
import java.util.List;

public interface DriverBO extends SuperBO {
    public boolean addDriver(DriverDTO driver) throws SQLException;
    public boolean updateDriver(DriverDTO driver) throws SQLException;
    public boolean deleteDriver(String id) throws SQLException;
    public String getId() throws SQLException;
    public String generateNewId(String id);
    public DriverDTO getDriver(String id) throws SQLException;
    public List<DriverDTO> getAll() throws SQLException;
    public List<String> getDriverId() throws SQLException;
    public List<String> getName() throws SQLException;
}
