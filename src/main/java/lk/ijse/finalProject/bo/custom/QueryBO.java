package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dto.ServiceTableDTO;
import lk.ijse.finalProject.entity.ServiceTable;

import java.sql.SQLException;
import java.util.List;

public interface QueryBO extends SuperBO {
    public List<ServiceTableDTO> getAllDetail() throws SQLException;
}
