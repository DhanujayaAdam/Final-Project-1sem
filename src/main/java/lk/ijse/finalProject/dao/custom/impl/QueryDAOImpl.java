package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.SqlUtil;
import lk.ijse.finalProject.dao.custom.QueryDAO;
import lk.ijse.finalProject.db.Dbconnection;
import lk.ijse.finalProject.dto.ServiceTableDTO;
import lk.ijse.finalProject.entity.ServiceTable;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public List<ServiceTable> getAllServiceDetail() throws SQLException {
        ResultSet resultSet = SqlUtil.execute("SELECT s.vehicle_id,s.description,s.date,s.service_center_id,tr.amount FROM Service s JOIN Transaction tr ON s.date=tr.date");
        List<ServiceTable> list = new ArrayList<>();
        while (resultSet.next()){
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
