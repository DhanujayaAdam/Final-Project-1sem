package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dao.SuperDAO;
import lk.ijse.finalProject.dto.DeliveryDetailDTO;

import java.sql.SQLException;

public interface DeliveryDetailBO extends SuperBO {
    public boolean saveDeliveryDetail(DeliveryDetailDTO detail) throws SQLException;
}
