package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.SqlUtil;
import lk.ijse.finalProject.dao.custom.DeliveryDetailDAO;
import lk.ijse.finalProject.db.Dbconnection;
import lk.ijse.finalProject.entity.DeliveryDetail;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DeliveryDetailDAOImpl implements DeliveryDetailDAO {
    public static boolean saveDetails(DeliveryDetail deliveryDetail) throws SQLException {
        String sql = "INSERT INTO Delivery_detail VALUES(?,?,?)";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,deliveryDetail.getShipmentId());
        pstm.setObject(2,deliveryDetail.getVehicleId());
        pstm.setObject(3,deliveryDetail.getDestination());
        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean add(DeliveryDetail obj) throws SQLException {
        return SqlUtil.execute("INSERT INTO Delivery_detail VALUES(?,?,?)",obj.getShipmentId(),obj.getVehicleId(),obj.getDestination());
    }

    @Override
    public boolean update(DeliveryDetail obj) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public String getId() throws SQLException {
        return null;
    }

    @Override
    public String generateNewId(String id) {
        return null;
    }

    @Override
    public DeliveryDetail getObject(String id) {
        return null;
    }

    @Override
    public List<DeliveryDetail> getAll() throws SQLException {
        return null;
    }
}
