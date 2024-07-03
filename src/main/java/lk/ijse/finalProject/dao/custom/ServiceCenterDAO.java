package lk.ijse.finalProject.dao.custom;

import lk.ijse.finalProject.dao.CrudDAO;
import lk.ijse.finalProject.entity.ServiceCenter;

import java.sql.SQLException;
import java.util.List;

public interface ServiceCenterDAO extends CrudDAO<ServiceCenter> {
    public List<String> getName() throws SQLException;
    public List<String> getPhone() throws SQLException;
    public List<String> getIds() throws SQLException;
}
