package lk.ijse.finalProject.repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.finalProject.DB.Dbconnection;
import lk.ijse.finalProject.model.Shipment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShipmentRepo {
    public static boolean inputShipment(Shipment shipment) throws SQLException {
        String sql = "INSERT INTO Shipment VALUES (?,?,?)";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        return pstm.executeUpdate() > 0;
    }

    public static ObservableList<String> getRouteId(String shipmentId) throws SQLException {
        ObservableList<String> obList = FXCollections.observableArrayList();
        String sql = "SELECT route_id FROM Shipment WHERE shipment_id = ?";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,shipmentId);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            obList.add(resultSet.getString(3));
        }
        return obList;
    }

    public static boolean saveShipment(Shipment shipment) throws SQLException {
        String sql = "INSERT INTO Shipment VALUES(?,?,?)";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,shipment.getShipment_id());
        pstm.setObject(2,shipment.getCost());
        pstm.setObject(3,shipment.getRoutId());
        return pstm.executeUpdate() > 0;
    }
}
