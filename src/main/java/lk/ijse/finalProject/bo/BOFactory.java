package lk.ijse.finalProject.bo;

import lk.ijse.finalProject.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){}
    public static BOFactory getBoFactory(){
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }
    public enum BoType{
        CLIENT,DELIVERY_DETAIL,DRIVER,SERVICE_SCHEDULE,USER,VEHICLE,VEHICLE_TO_BE_SERVICED,PACKAGE,ROUTE,SERVICE,SERVICE_CENTER,SHIPMENT,PAYMENT,JOIN_QUERY
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
            case PACKAGE:
                return new PackageBOImpl();
            case ROUTE:
                return new RouteBOImpl();
            case SERVICE:
                return new ServiceBOImpl();
            case SERVICE_CENTER:
                return new ServiceCenterBOImpl();
            case SHIPMENT:
                return new ShipmentBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case JOIN_QUERY:
                return new QueryBOImpl();
            default:
                return null;
        }
    }
}
