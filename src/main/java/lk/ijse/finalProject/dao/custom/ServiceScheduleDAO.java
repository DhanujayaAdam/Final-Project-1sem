package lk.ijse.finalProject.dao.custom;

import lk.ijse.finalProject.dao.CrudDAO;
import lk.ijse.finalProject.entity.ServiceSchedule;

import java.sql.SQLException;
import java.util.List;

public interface ServiceScheduleDAO extends CrudDAO<ServiceSchedule> {
    public String getShedule(String type) throws SQLException;
    public List<String> getType() throws SQLException;
}
