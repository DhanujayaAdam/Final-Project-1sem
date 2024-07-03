package lk.ijse.finalProject.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.finalProject.bo.BOFactory;
import lk.ijse.finalProject.bo.custom.RouteBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.custom.RouteDAO;
import lk.ijse.finalProject.dao.custom.impl.RoutDAOImpl;
import lk.ijse.finalProject.dto.RouteDTO;
import lk.ijse.finalProject.entity.Route;

import java.sql.SQLException;
import java.util.List;

public class RouteBOImpl implements RouteBO {
    RouteDAO routeDAO = (RouteDAO) DAOFactory.getDaoFactory().getInstance(DAOFactory.DaoType.ROUTE);
    @Override
    public boolean addRoute(RouteDTO obj) throws SQLException {
        return routeDAO.add(new Route(obj.getId(), obj.getLocation(), obj.getDestination(), obj.getDescription(), obj.getDistance()));
    }

    @Override
    public boolean updateRoute(RouteDTO obj) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteRoute(String id) throws SQLException {
        return false;
    }

    @Override
    public String getRouteId() throws SQLException {
        return routeDAO.getId();
    }

    @Override
    public String generateNewRouteId(String id) {
        return null;
    }

    @Override
    public RouteDTO getRouteObject(String id) throws SQLException {
        return null;
    }

    @Override
    public List<RouteDTO> getAllRoute() throws SQLException {
        return null;
    }

    @Override
    public String getDestination(String routId) throws SQLException {
        return routeDAO.getDestination(routId);
    }

    @Override
    public String getDistance(String routId) throws SQLException {
        return routeDAO.getDistance(routId);
    }

    @Override
    public String getDescription(String routeId) throws SQLException {
        return routeDAO.getDescription(routeId);
    }

    @Override
    public List<String> getIdList() throws SQLException {
        return routeDAO.getList();
    }
}
