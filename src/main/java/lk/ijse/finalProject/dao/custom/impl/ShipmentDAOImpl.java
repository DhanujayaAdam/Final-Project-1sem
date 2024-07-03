package lk.ijse.finalProject.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.finalProject.dao.SqlUtil;
import lk.ijse.finalProject.dao.custom.ShipmentDAO;
import lk.ijse.finalProject.db.Dbconnection;
import lk.ijse.finalProject.entity.Shipment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ShipmentDAOImpl implements ShipmentDAO {
    @Override
    public boolean add(Shipment obj) throws SQLException {
        return SqlUtil.execute("INSERT INTO Shipment VALUES(?,?,?)",obj.getShipment_id(),obj.getCost(),obj.getRoutId());
    }

    @Override
    public boolean update(Shipment obj) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public String getId() throws SQLException {
        ResultSet rst = SqlUtil.execute("SELECT shipment_id FROM Shipment ORDER BY shipment_id DESC LIMIT 1");
        if(rst.next())
            return rst.getString("shipment_id");
        return null;
    }

    @Override
    public String generateNewId(String id) {
        return null;
    }

    @Override
    public Shipment getObject(String id) throws SQLException {
        return null;
    }

    @Override
    public List<Shipment> getAll() throws SQLException {
        return null;
    }

    @Override
    public ObservableList<String> getIdList() throws SQLException {
       ResultSet rst = SqlUtil.execute("SELECT route_id FROM Shipment WHERE shipment_id = ?");
       ObservableList<String> idList = FXCollections.observableArrayList();
       while (rst.next()){
           idList.add(rst.getString("route_id"));
       }
       return idList;
    }
}
