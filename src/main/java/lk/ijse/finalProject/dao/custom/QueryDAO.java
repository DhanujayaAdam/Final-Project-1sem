package lk.ijse.finalProject.dao.custom;

import lk.ijse.finalProject.dao.SuperDAO;
import lk.ijse.finalProject.dto.ServiceTableDTO;
import lk.ijse.finalProject.entity.ServiceTable;

import java.sql.SQLException;
import java.util.List;

public interface QueryDAO extends SuperDAO {
    public List<ServiceTable> getAllServiceDetail() throws SQLException;
}
