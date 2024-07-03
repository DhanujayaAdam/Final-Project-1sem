package lk.ijse.finalProject.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.finalProject.bo.BOFactory;
import lk.ijse.finalProject.bo.custom.ShipmentBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.custom.ShipmentDAO;
import lk.ijse.finalProject.dao.custom.impl.ShipmentDAOImpl;
import lk.ijse.finalProject.dto.ShipmentDTO;
import lk.ijse.finalProject.entity.Shipment;

import java.sql.SQLException;
import java.util.List;

public class ShipmentBOImpl implements ShipmentBO {
    ShipmentDAO shipmentDAO = (ShipmentDAO) DAOFactory.getDaoFactory().getInstance(DAOFactory.DaoType.SHIPMENT);
    @Override
    public String getCurrentShipmenId() throws SQLException {
        return shipmentDAO.getId();
    }

    @Override
    public boolean addShipment(ShipmentDTO obj) throws SQLException {
        return shipmentDAO.add(new Shipment(obj.getShipment_id(),obj.getCost(),obj.getRoutId()));
    }

    @Override
    public boolean updateShipment(ShipmentDTO obj) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteShipment(String id) throws SQLException {
        return false;
    }

    @Override
    public String generateNewShipmentId(String id) {
        return null;
    }

    @Override
    public ShipmentDTO getShipmentObject(String id) throws SQLException {
        return null;
    }

    @Override
    public List<ShipmentDTO> getAllShipment() throws SQLException {
        return null;
    }

    @Override
    public ObservableList<String> getRouteIdList(String shipmentId) throws SQLException {
        return shipmentDAO.getIdList();
    }
}
