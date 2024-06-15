package lk.ijse.finalProject.repository;

import lk.ijse.finalProject.DB.Dbconnection;
import lk.ijse.finalProject.model.VehicleSave;

import java.sql.Connection;
import java.sql.SQLException;

public class VehicleSaveRepo  {
    public static boolean saveVehicle(VehicleSave vs) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            System.out.println("come to try catch");
        boolean isRegistered = VehicleRepo.registerVehicle(vs.getVehicle());
        if (isRegistered){
            System.out.println("invoke state1");
            boolean isSaved = VehicleToBeServiceRepo.addReport(vs.getVehicleService());
            boolean isAllCorrect = VehicleToBeServiceRepo.addReport(vs.getTyreReplacement());
            if (isSaved && isAllCorrect){
                System.out.println("invoke state2");
                connection.commit();
                return true;
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
