package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.SqlUtil;
import lk.ijse.finalProject.dao.custom.ServiceScheduleDAO;
import lk.ijse.finalProject.db.Dbconnection;
import lk.ijse.finalProject.entity.ServiceSchedule;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceScheduleDAOImpl implements ServiceScheduleDAO {
    public static String getShchedule1(String vehicleService) throws SQLException {
        String sql = "SELECT service_schedule_id FROM Service_schedule WHERE service_type = ?";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,vehicleService);
        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    public static List<String> getType() throws SQLException {
        String sql = "SELECT service_type FROM Service_schedule";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        List<String> typeList = new ArrayList<>();
        while (resultSet.next()){
            typeList.add(resultSet.getString("service_type"));
        }
        return typeList;
    }

    @Override
    public boolean add(ServiceSchedule obj) throws SQLException {
        return false;
    }

    @Override
    public boolean update(ServiceSchedule obj) throws SQLException {
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
    public ServiceSchedule getObject(String id) throws SQLException {
        return null;
    }

    @Override
    public List<ServiceSchedule> getAll() throws SQLException {
        return null;
    }

    @Override
    public String getShedule(String type) throws SQLException {
        ResultSet rst = SqlUtil.execute("SELECT service_schedule_id FROM Service_schedule WHERE service_type = ?",type);
        rst.next();
        return rst.getString("service_schedule_id");
    }
}
