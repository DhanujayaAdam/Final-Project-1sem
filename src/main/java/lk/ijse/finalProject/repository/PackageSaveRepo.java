package lk.ijse.finalProject.repository;

import lk.ijse.finalProject.DB.Dbconnection;
import lk.ijse.finalProject.controller.mail.Mail;
import lk.ijse.finalProject.model.Driver;
import lk.ijse.finalProject.model.PackageSave;
import lk.ijse.finalProject.model.VehicleToBeService;

import java.sql.Connection;
import java.sql.SQLException;

public class PackageSaveRepo {
    public static boolean placeClientOrder(PackageSave packageSave) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try{
            System.out.println("Invoke to first statement");
        boolean isSave = ShipmentRepo.saveShipment(packageSave.getShipment());
        if (isSave){
            System.out.println("invoke to statement1");
            boolean isSaved = PackageRepo.savePackage(packageSave.getAPackage());
            if (isSaved){
                System.out.println("Invoke to statement2");
                boolean isOk = DeliveryDetailRepo.saveDetails(packageSave.getDeliveryDetail());
                if (isOk){
                    System.out.println("Invoke to statement3");
                    boolean isUpdated = VehicleRepo.updateCurrentDistance(packageSave.getRoute().getDistance(), packageSave.getDeliveryDetail().getVehicleId());
                    if (isUpdated){
                        System.out.println("Invoke to statement4");
                        double currentDistance = VehicleToBeServiceRepo.getCurrentDistance(packageSave.getVehicleId());
                        if (currentDistance >= 5000){
                            System.out.println("get current distance ?");
                            String title = "Vehicle Service Information";
                            String body = "Vehicle " + packageSave.getVehicleId() + ",Your service time has come";
                            Driver dr = DriverRepo.getDetail(packageSave.getVehicleId());
                            System.out.println("dr :" + dr);
                            String email = dr.getEmail();
                            System.out.println(email);
                            Mail mail = new Mail();
                            mail.setMail(email,title,body);
                            System.out.println("Send successfull");
                        }if(currentDistance >=10000){
                            String title = "Tyre Replacement Information";
                            String body = "Vehicle " + packageSave.getVehicleId() + ",Your Tyre Replacement has come";
                            Driver dr = DriverRepo.getDetail(packageSave.getVehicleId());
                            String email = dr.getEmail();
                            Mail mail = new Mail();
                            mail.setMail(email,title,body);
                        }if(currentDistance == 10100){
                            VehicleToBeServiceRepo.clearDistance(packageSave.getVehicleId());
                        }
                        System.out.println("Invoke to this");
                        boolean isUpdate = VehicleToBeServiceRepo.update(packageSave.getVehicleId(), packageSave.getRoute().getDistance());
                        if (isUpdate){
                            System.out.println("Commit");
                            connection.commit();
                            return true;
                        }
                    }
                }
            }
        }
        connection.rollback();
        return false;
    } catch (Exception e){
        connection.rollback();
        return false;
        }
    }
}
