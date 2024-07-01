package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.db.Dbconnection;
import lk.ijse.finalProject.entity.Service;
import lk.ijse.finalProject.entity.ServiceTable;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAOImpl {
    public static String  getAvailableId() throws SQLException {
        String sql = "SELECT service_id FROM Service ORDER BY service_id DESC LIMIT 1";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    public static String  getCurrentId(String currentId) {
        if (currentId != null){
            String[] split = currentId.split("SE");
            int idNum = Integer.parseInt(split[1]);
            return "SE" + ++idNum;
        }
        return "SE1";
    }

    public static boolean saveService(Service service) throws SQLException {
        String sql = "INSERT INTO Service VALUES(?,?,?,?,?,?)";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,service.getId());
        pstm.setObject(2,service.getVehicleId());
        pstm.setObject(3,service.getServiceType());
        pstm.setObject(4,service.getDescription());
        pstm.setObject(5,service.getDate());
        pstm.setObject(6,service.getServiceCenterId());
        return pstm.executeUpdate() > 0;
    }

    public static Service getAll() throws SQLException {
        String sql = "SELECT * FROM Service";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            String id = resultSet.getString(1);
            String vehiId = resultSet.getString(2);
            String type = resultSet.getString(3);
            String description = resultSet.getString(4);
            Date date = resultSet.getDate(5);
            String centerId = resultSet.getString(6);
            Service service = new Service(id,vehiId,type,description,date,centerId);
            return service;
        }
        return null;
    }

    public static boolean updateService(Service service) throws SQLException {
        String sql = "UPDATE Service SET vehicle_id = ?,service_type=?,description=?,date=?,service_center_id=? WHERE service_id=?";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,service.getVehicleId());
        pstm.setObject(2,service.getServiceType());
        pstm.setObject(3,service.getDescription());
        pstm.setObject(4,service.getDate());
        pstm.setObject(5,service.getServiceCenterId());
        pstm.setObject(6,service.getId());
        return pstm.executeUpdate() > 0;
    }

    public static List<ServiceTable> getAllDetail() throws SQLException {
        String sql = "SELECT s.vehicle_id,s.description,s.date,s.service_center_id,tr.amount FROM Service s JOIN Transaction tr ON s.date=tr.date";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        List<ServiceTable> list = new ArrayList<>();
        if (resultSet.next()){
            String vehicleId = resultSet.getString("vehicle_id");
            String description = resultSet.getString("description");
            String centerId = resultSet.getString("service_center_id");
            Date date = Date.valueOf(resultSet.getString("date"));
            double amount = resultSet.getDouble("amount");
            ServiceTable serviceTable = new ServiceTable(vehicleId,description,date,centerId,amount);
            list.add(serviceTable);
        }
        return list;
    }
}
