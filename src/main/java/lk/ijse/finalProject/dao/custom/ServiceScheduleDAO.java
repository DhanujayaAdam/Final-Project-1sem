package lk.ijse.finalProject.dao.custom;

import lk.ijse.finalProject.dao.CrudDAO;
import lk.ijse.finalProject.entity.ServiceSchedule;

import java.sql.SQLException;

public interface ServiceScheduleDAO extends CrudDAO<ServiceSchedule> {
    public String getShedule(String type) throws SQLException;

}
