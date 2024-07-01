package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.db.Dbconnection;
import lk.ijse.finalProject.entity.ServisePayment;

import java.sql.Connection;
import java.sql.SQLException;

public class ServicePaymentRepo {
    public static boolean save(ServisePayment sp) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isSaved = ServiceDAOImpl.saveService(sp.getService());
            if (isSaved) {
                boolean isSave = PaymentDAOImpl.savePayment(sp.getPayment());
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
            boolean isUpdate = ServiceDAOImpl.updateService(sp.getService());
            if (isUpdate) {
                boolean isUpdated = PaymentDAOImpl.updatePayment(sp.getPayment());
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
