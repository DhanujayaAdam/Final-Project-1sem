package lk.ijse.finalProject.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.finalProject.dao.SqlUtil;
import lk.ijse.finalProject.dao.custom.RouteDAO;
import lk.ijse.finalProject.db.Dbconnection;
import lk.ijse.finalProject.entity.Route;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoutDAOImpl implements RouteDAO {

    @Override
    public String getDestination(String routId) throws SQLException {
        ResultSet rst = SqlUtil.execute("SELECT last_location FROM Route WHERE route_id = ?",routId);
        rst.next();
        return rst.getString("last_location");
    }
    @Override
    public String getDistance(String routId) throws SQLException {
        ResultSet resultSet = SqlUtil.execute("SELECT distance FROM Route WHERE route_id = ?",routId);
        resultSet.next();
        return resultSet.getString("distance");
    }
    @Override
    public String getDescription(String routId) throws SQLException {
        ResultSet resultSet = SqlUtil.execute("SELECT route_description FROM Route WHERE route_id = ?",routId);
        resultSet.next();
        return resultSet.getString("route_description");
    }

    @Override
    public List<String> getList() throws SQLException {
        ResultSet rst = SqlUtil.execute("SELECT route_id FROM Route");
        List<String> idList = new ArrayList<>();
        while (rst.next()){
            idList.add(rst.getString("route_id"));
        }
        return idList;
    }
    @Override
    public boolean add(Route obj) throws SQLException {
        return SqlUtil.execute("INSERT INTO Route VALUES(?,?,?,?,?)",obj.getId(), obj.getLocation(), obj.getDestination(), obj.getDescription(), obj.getDistance());
    }

    @Override
    public boolean update(Route obj) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public String getId() throws SQLException {
        ResultSet rst = SqlUtil.execute("SELECT route_id FROM Route ORDER BY route_id DESC LIMIT 1");
        if (rst.next())
            return rst.getString("route_id");
        return null;
    }

    @Override
    public String generateNewId(String id) {
        return null;
    }

    @Override
    public Route getObject(String id) throws SQLException {
        return null;
    }

    @Override
    public List<Route> getAll() throws SQLException {
        return null;
    }
}
