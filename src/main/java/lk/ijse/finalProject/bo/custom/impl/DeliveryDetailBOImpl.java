package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.custom.DeliveryDetailBO;
import lk.ijse.finalProject.dao.custom.DeliveryDetailDAO;
import lk.ijse.finalProject.dao.custom.impl.DeliveryDetailDAOImpl;
import lk.ijse.finalProject.dto.DeliveryDetailDTO;
import lk.ijse.finalProject.entity.DeliveryDetail;

import java.sql.SQLException;

public class DeliveryDetailBOImpl implements DeliveryDetailBO {
    DeliveryDetailDAO deliveryDetailDAO = new DeliveryDetailDAOImpl();
    @Override
    public boolean saveDeliveryDetail(DeliveryDetailDTO detail) throws SQLException {
        return deliveryDetailDAO.add(new DeliveryDetail(detail.getShipmentId(),detail.getVehicleId(),detail.getDestination()));
    }
}
