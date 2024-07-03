package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.CrudDAO;
import lk.ijse.finalProject.dao.SqlUtil;
import lk.ijse.finalProject.dao.custom.ServiceCenterDAO;
import lk.ijse.finalProject.db.Dbconnection;
import lk.ijse.finalProject.entity.ServiceCenter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceCenterDAOImpl implements ServiceCenterDAO {
    @Override
    public boolean add(ServiceCenter obj) throws SQLException {
        return SqlUtil.execute("INSERT INTO Service_center VALUES(?,?,?,?,?)",obj.getId(),obj.getName(),obj.getLocation(),obj.getTel(),obj.getEmail());
    }
    @Override
    public boolean update(ServiceCenter serviceCenter) throws SQLException {
        return SqlUtil.execute("UPDATE Service_center SET center_name = ?,location = ?,contact_number = ?,email = ? WHERE service_center_id =?",serviceCenter.getName(),serviceCenter.getLocation(),serviceCenter.getTel(),serviceCenter.getEmail(),serviceCenter.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public String getId() throws SQLException {
        ResultSet rst = SqlUtil.execute("SELECT service_center_id FROM Service_center ORDER BY service_center_id DESC LIMIT 1");
        if (rst.next())
            return rst.getString("service_center_id");
        return null;
    }
    @Override
    public List<String> getIds() throws SQLException {
        ResultSet resultSet = SqlUtil.execute("SELECT service_center_id FROM Service_center");
        List<String> idlist = new ArrayList<>();
        while (resultSet.next()){
            idlist.add(resultSet.getString(1));
        }
        return idlist;
    }

    @Override
    public String generateNewId(String id) {
        return null;
    }

    @Override
    public ServiceCenter getObject(String id) throws SQLException {
        ResultSet rst = SqlUtil.execute("SELECT * FROM Service_center");
        rst.next();
        return new ServiceCenter(rst.getString("service_center_id"),rst.getString("center_name"),rst.getString("location"),rst.getString("contact_number"),rst.getString("email"));

    }

    @Override
    public List<ServiceCenter> getAll() throws SQLException {
        return null;
    }
    @Override
    public  List<String> getName() throws SQLException {
        ResultSet resultSet = SqlUtil.execute("SELECT center_name FROM Service_center ORDER BY service_center_id DESC LIMIT 5");
        List<String> nameList = new ArrayList<>();
        while (resultSet.next()){
            nameList.add(resultSet.getString("center_name"));
        }
        return nameList;
    }
    @Override
    public List<String> getPhone() throws SQLException {
        ResultSet resultSet = SqlUtil.execute("SELECT contact_number,service_center_id FROM Service_center ORDER BY service_center_id DESC LIMIT 5");
        List<String> phoneList = new ArrayList<>();
        while(resultSet.next()){
            String contactNumber = resultSet.getString("contact_number");
            String centerId = resultSet.getString("service_center_id");
            String value = centerId + "- " + contactNumber;
            phoneList.add(value);
        }
        return phoneList;
    }
}
