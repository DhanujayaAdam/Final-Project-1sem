package lk.ijse.finalProject.dao.custom;

import com.mysql.cj.jdbc.result.CachedResultSetMetaData;
import lk.ijse.finalProject.dao.CrudDAO;
import lk.ijse.finalProject.entity.Driver;

import java.sql.SQLException;
import java.util.List;

public interface DriverDAO extends CrudDAO<Driver> {
    public List<String> driverId() throws SQLException;
    public List<String> getDriverName() throws SQLException;
}
