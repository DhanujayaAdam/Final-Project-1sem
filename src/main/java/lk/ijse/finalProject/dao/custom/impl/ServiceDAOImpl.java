package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.SqlUtil;
import lk.ijse.finalProject.dao.custom.ServiceDAO;
import lk.ijse.finalProject.db.Dbconnection;
import lk.ijse.finalProject.entity.Service;
import lk.ijse.finalProject.entity.ServiceTable;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAOImpl implements ServiceDAO {
    @Override
    public boolean add(Service obj) throws SQLException {
        return SqlUtil.execute("INSERT INTO Service VALUES(?,?,?,?,?,?)",obj.getId(),obj.getVehicleId(),obj.getServiceType(),obj.getDescription(),obj.getDate(),obj.getServiceCenterId());
    }

    @Override
    public boolean update(Service obj) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public String getId() throws SQLException {
        ResultSet rst = SqlUtil.execute("SELECT service_id FROM Service ORDER BY service_id DESC LIMIT 1");
        if(rst.next())
          return rst.getString("service_id");
        return null;
    }

    @Override
    public String generateNewId(String id) {
        return null;
    }

    @Override
    public Service getObject(String id) throws SQLException {
       ResultSet resultSet = SqlUtil.execute("SELECT * FROM Service WHERE service_id = ?",id);
       resultSet.next();
       return new Service(resultSet.getString("service_id"),resultSet.getString("vehicle_id"),resultSet.getString("service_type"),resultSet.getString("description"),resultSet.getDate("date"),resultSet.getString("service_center_id"));
    }

    @Override
    public List<Service> getAll() throws SQLException {
        return null;
    }
}
