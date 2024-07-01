package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.custom.ServiceSheduleBO;
import lk.ijse.finalProject.dao.custom.ServiceScheduleDAO;
import lk.ijse.finalProject.dao.custom.impl.ServiceScheduleDAOImpl;

import java.sql.SQLException;

public class ServiceScheduleBOImpl implements ServiceSheduleBO {
    ServiceScheduleDAO serviceScheduleDAO = new ServiceScheduleDAOImpl();
    @Override
    public String getShchedule(String type) throws SQLException {
        return serviceScheduleDAO.getShedule(type);
    }
}
