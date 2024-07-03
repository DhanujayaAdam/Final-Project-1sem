package lk.ijse.finalProject.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dto.RouteDTO;

import java.sql.SQLException;
import java.util.List;

public interface RouteBO extends SuperBO {
    public boolean addRoute(RouteDTO obj) throws SQLException;
    public boolean updateRoute(RouteDTO obj) throws SQLException;
    public boolean deleteRoute(String id) throws SQLException;
    public String getRouteId() throws SQLException;
    public String generateNewRouteId(String id);
    public RouteDTO getRouteObject(String id) throws SQLException;
    public List<RouteDTO> getAllRoute() throws SQLException;
    public String getDestination(String routId) throws SQLException;
    public String getDistance(String routId) throws SQLException;
    public String getDescription(String routId) throws SQLException;
    public List<String> getIdList() throws SQLException;
}
