package lk.ijse.finalProject.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.finalProject.dao.CrudDAO;
import lk.ijse.finalProject.entity.Shipment;

import java.sql.SQLException;

public interface ShipmentDAO extends CrudDAO<Shipment> {
    public ObservableList<String> getIdList() throws SQLException;
}
