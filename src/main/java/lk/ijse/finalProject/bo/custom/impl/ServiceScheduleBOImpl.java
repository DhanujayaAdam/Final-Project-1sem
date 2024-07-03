package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.BOFactory;
import lk.ijse.finalProject.bo.custom.ServiceSheduleBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.custom.ServiceScheduleDAO;
import lk.ijse.finalProject.dao.custom.impl.ServiceScheduleDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class ServiceScheduleBOImpl implements ServiceSheduleBO {
    ServiceScheduleDAO serviceScheduleDAO = (ServiceScheduleDAO) DAOFactory.getDaoFactory().getInstance(DAOFactory.DaoType.SERVICE_SCHEDULE);
    @Override
    public String getShchedule(String type) throws SQLException {
        return serviceScheduleDAO.getShedule(type);
    }
    @Override
    public List<String> getType() throws SQLException {
        return serviceScheduleDAO.getType();
    }
}
