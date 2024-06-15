package lk.ijse.finalProject.repository;

import lk.ijse.finalProject.DB.Dbconnection;
import lk.ijse.finalProject.model.DeliveryDetail;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeliveryDetailRepo {
    public static boolean saveDetails(DeliveryDetail deliveryDetail) throws SQLException {
        String sql = "INSERT INTO Delivery_detail VALUES(?,?,?)";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,deliveryDetail.getShipmentId());
        pstm.setObject(2,deliveryDetail.getVehicleId());
        pstm.setObject(3,deliveryDetail.getDestination());
        return pstm.executeUpdate() > 0;
    }
}
