package lk.ijse.finalProject.repository;

import com.mysql.cj.xdevapi.DbDocValueFactory;
import lk.ijse.finalProject.DB.Dbconnection;
import lk.ijse.finalProject.model.Route;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteRepo {

    public static String getCurrentRouteId() throws SQLException {
        String sql = "SELECT route_id FROM Route ORDER BY route_id DESC LIMIT 1";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next())
            return resultSet.getString(1);
        return null;
    }

    public static String  getNextAvailableId(String currentRouteId) {
        if (currentRouteId != null){
            String[] split = currentRouteId.split("R");
            int idNum = Integer.parseInt(split[1]);
            return "R" + ++idNum;
        }
        return "R1";
    }

    public static boolean saveRoute(Route route) throws SQLException {
        String sql ="INSERT INTO Route VALUES(?,?,?,?,?)";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,route.getId());
        pstm.setObject(2,route.getLocation());
        pstm.setObject(3,route.getDestination());
        pstm.setObject(4,route.getDescription());
        pstm.setObject(5,route.getDistance());
        return pstm.executeUpdate() > 0;
    }

    public static String getDestination(String routId) throws SQLException {
        String sql = "SELECT last_location FROM Route WHERE route_id = ?";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,routId);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return resultSet.getString("last_location");
        }
        return null;
    }

    public static String getMode(String destination) {
        if (destination.equals("Colombo")){
            return "Ship";
        } else if (destination.equals("Katunayake")){
            return "AirPlane";
        } else {
            return "Ship";
        }
    }

    public static String getDistance(String routId) throws SQLException {
        String sql = "SELECT distance FROM Route WHERE route_id = ?";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,routId);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return resultSet.getString("distance");
        }
        return null;
    }

    public static String getDescription(String routId) throws SQLException {
        String sql = "SELECT route_description FROM Route WHERE route_id = ?";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,routId);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return resultSet.getString("route_description");
        }
        return null;
    }

    public static List<String > getId() throws SQLException {
        String sql = "SELECT route_id FROM Route";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        List<String> idList = new ArrayList<>();
        while(resultSet.next()){
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }
}
