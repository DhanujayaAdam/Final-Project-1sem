package lk.ijse.finalProject.dao;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.bo.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){}
    public static DAOFactory getDaoFactory(){
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }
    public enum DaoType{
        CLIENT,DELIVERY_DETAIL,DRIVER,SERVICE_SCHEDULE,USER,VEHICLE,VEHICLE_TO_BE_SERVICED
    }
    public SuperBO getInstance(DaoType daoType){
        switch (daoType){
            case CLIENT :
                return new ClientBOImpl();
            case DELIVERY_DETAIL:
                return new DeliveryDetailBOImpl();
            case DRIVER:
                return new DriverBOImpl();
            case SERVICE_SCHEDULE:
                return new ServiceScheduleBOImpl();
            case USER:
                return new UserBOImpl();
            case VEHICLE:
                return new VehicleBOImpl();
            case VEHICLE_TO_BE_SERVICED:
                return new VehicleTOBeServicedBOImpl();
            default:
                return null;
        }
    }
}
