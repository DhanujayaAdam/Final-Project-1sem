package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.SqlUtil;
import lk.ijse.finalProject.dao.custom.PackageDAO;
import lk.ijse.finalProject.db.Dbconnection;
import lk.ijse.finalProject.entity.Package;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class PackageDAOImpl implements PackageDAO {
    @Override
    public List<String> getCompanyId() throws SQLException {
        ResultSet resultSet = SqlUtil.execute("SELECT client_company_id FROM Client_order ORDER BY order_id DESC LIMIT 4");
        List<String> idList = new ArrayList<>();
        while (resultSet.next()){
            idList.add(resultSet.getString(1));
        }
        return idList;
    }
    @Override
    public String getTrackingNumber() throws SQLException {
        ResultSet resultSet = SqlUtil.execute("SELECT tracking_number FROM Client_order ORDER BY order_id DESC LIMIT 5");
        if (resultSet.next())
            return resultSet.getString("tracking_number");
        return null;
    }
    @Override
    public String getVehicleId(String packageId) throws SQLException {
        ResultSet rst = SqlUtil.execute("SELECT vehicle_id FROM Client_order WHERE package_id = ?",packageId);
        rst.next();
        return rst.getString("vehicle_id");
    }
    @Override
    public int getPackageCount1(String c1) throws SQLException {
        ResultSet rst = SqlUtil.execute("SELECT COUNT(*) package_count FROM Client_order WHERE client_company_id =?",c1);
        if(rst.next())
            return rst.getInt("package_count");
        return 0;
    }
    @Override
    public boolean add(Package obj) throws SQLException {
        return SqlUtil.execute("INSERT INTO Client_order VALUES(?,?,?,?,?,?,?,?)",obj.getOrderId(),obj.getTrackingNumber(),obj.getCompanyId(),obj.getTypeOfGood(),obj.getWeight(),obj.getDeliveryType(),obj.getBorrowDAte(),obj.getShipmentId());
    }

    @Override
    public boolean update(Package obj) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public String getId() throws SQLException {
        ResultSet rst = SqlUtil.execute("SELECT order_id FROM Client_order ORDER BY order_id DESC LIMIT 1");
        if (rst.next())
            return rst.getString("order_id");
        return null;
    }
    @Override
    public String generateNewId(String id) {
        return null;
    }

    @Override
    public Package getObject(String id) throws SQLException {
        return null;
    }
    @Override
    public List<Package> getAll() throws SQLException {
        ResultSet rst = SqlUtil.execute("SELECT * FROM Client_order");
        List<Package> packageList = new ArrayList<>();
        while (rst.next()){
            packageList.add(new Package(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4), rst.getDouble(5),rst.getString(6),rst.getDate(7),rst.getString(8)));
        }
        return packageList;
    }
    @Override
    public List<String> getTypeOfGood() throws SQLException {
        ResultSet resultSet = SqlUtil.execute("SELECT type_of_good FROM Client_order ORDER BY order_id DESC LIMIT 5");
        List<String> typeList = new ArrayList<>();
        while (resultSet.next()){
            typeList.add(resultSet.getString("type_of_good"));
        }
        return typeList;
    }
    @Override
    public List<String> getTrackingNumbers() throws SQLException {
        ResultSet resultSet = SqlUtil.execute("SELECT tracking_number FROM Client_order ORDER BY order_id DESC LIMIT 4");
        List<String> trackList = new ArrayList<>();
        while (resultSet.next()){
            trackList.add("Tracking Number- " + resultSet.getString("tracking_number"));
        }
        return trackList;
    }
    @Override
    public List<String> getPackageList() throws SQLException {
       ResultSet resultSet = SqlUtil.execute("SELECT tracking_number FROM Client_order ORDER BY order_id DESC LIMIT 5");
       ArrayList<String> trackingList = new ArrayList<>();
       while (resultSet.next()){
           trackingList.add(resultSet.getString("tracking_number"));
       }
       return trackingList;
    }
}
