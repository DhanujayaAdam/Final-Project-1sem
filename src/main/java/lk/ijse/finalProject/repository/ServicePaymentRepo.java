package lk.ijse.finalProject.repository;

import lk.ijse.finalProject.DB.Dbconnection;
import lk.ijse.finalProject.model.ServisePayment;

import java.sql.Connection;
import java.sql.SQLException;

public class ServicePaymentRepo {
    public static boolean save(ServisePayment sp) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isSaved = ServiceRepo.saveService(sp.getService());
            if (isSaved) {
                boolean isSave = PaymentRepo.savePayment(sp.getPayment());
                if (isSave) {
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;
        }catch (Exception e){
            connection.rollback();
            return false;
        }
    }

    public static boolean update(ServisePayment sp) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            boolean isUpdate = ServiceRepo.updateService(sp.getService());
            if (isUpdate) {
                boolean isUpdated = PaymentRepo.updatePayment(sp.getPayment());
                if (isUpdated) {
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;
        }catch (Exception e){
            connection.rollback();
            return false;
        }
    }
}
