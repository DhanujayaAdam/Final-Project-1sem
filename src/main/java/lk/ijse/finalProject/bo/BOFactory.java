package lk.ijse.finalProject.bo;

import lk.ijse.finalProject.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){}
    public static BOFactory getBoFactory(){
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }
    public enum BoType{
        CLIENT,DELIVERY_DETAIL,DRIVER,SERVICE_SCHEDULE,USER,VEHICLE,VEHICLE_TO_BE_SERVICED
    }
    public SuperBO getInstance(BoType boType){
        switch (boType){
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
