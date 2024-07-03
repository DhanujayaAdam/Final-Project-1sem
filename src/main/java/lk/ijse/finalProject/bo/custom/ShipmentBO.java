package lk.ijse.finalProject.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dto.ShipmentDTO;

import java.sql.SQLException;
import java.util.List;

public interface ShipmentBO extends SuperBO {
    public String getCurrentShipmenId() throws SQLException;
    public boolean addShipment(ShipmentDTO obj) throws SQLException;
    public boolean updateShipment(ShipmentDTO obj) throws SQLException;
    public boolean deleteShipment(String id) throws SQLException;
    public String generateNewShipmentId(String id);
    public ShipmentDTO getShipmentObject(String id) throws SQLException;
    public List<ShipmentDTO> getAllShipment() throws SQLException;
    public ObservableList<String> getRouteIdList(String shipmentId) throws SQLException;
}
