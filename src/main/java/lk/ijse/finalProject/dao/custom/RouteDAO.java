package lk.ijse.finalProject.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.finalProject.dao.CrudDAO;
import lk.ijse.finalProject.entity.Route;

import java.sql.SQLException;
import java.util.List;

public interface RouteDAO extends CrudDAO<Route> {
    public String getDestination(String id) throws SQLException;
    public String getDistance(String routId) throws SQLException;
    public String getDescription(String routeId) throws SQLException;
    public List<String> getList() throws SQLException;
}
