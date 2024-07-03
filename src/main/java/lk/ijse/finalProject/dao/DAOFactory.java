package lk.ijse.finalProject.dao;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.bo.custom.impl.*;
import lk.ijse.finalProject.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){}
    public static DAOFactory getDaoFactory(){
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }
    public enum DaoType{
        CLIENT,DELIVERY_DETAIL,DRIVER,SERVICE_SCHEDULE,USER,VEHICLE,VEHICLE_TO_BE_SERVICED,PACKAGE,ROUTE,SERVICE,SERVICE_CENTER,SHIPMENT,PAYMENT,JOIN_QUERY
    }
    public SuperDAO getInstance(DaoType daoType){
        switch (daoType){
            case CLIENT :
                return new ClientDAOImpl();
            case DELIVERY_DETAIL:
                return new DeliveryDetailDAOImpl();
            case DRIVER:
                return new DriverDAOImpl();
            case SERVICE_SCHEDULE:
                return new ServiceScheduleDAOImpl();
            case USER:
                return new UserDAOImpl();
            case VEHICLE:
                return new VehicleDAOImpl();
            case VEHICLE_TO_BE_SERVICED:
                return new VehicleToBeServicedDAOImpl();
            case PACKAGE:
                return new PackageDAOImpl();
            case ROUTE:
                return new RoutDAOImpl();
            case SERVICE:
                return new ServiceDAOImpl();
            case SERVICE_CENTER:
                return new ServiceCenterDAOImpl();
            case SHIPMENT:
                return new ShipmentDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case JOIN_QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }
}
