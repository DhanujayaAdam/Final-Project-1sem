package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dao.SuperDAO;

import java.sql.SQLException;
import java.util.List;

public interface ServiceSheduleBO extends SuperBO {
    public String getShchedule(String vehicleService) throws SQLException;
    public List<String> getType() throws SQLException;
}
