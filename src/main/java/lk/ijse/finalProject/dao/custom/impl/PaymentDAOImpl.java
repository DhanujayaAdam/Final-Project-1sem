package lk.ijse.finalProject.dao.custom.impl;

import javafx.collections.FXCollections;
import lk.ijse.finalProject.db.Dbconnection;
import lk.ijse.finalProject.entity.Payment;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PaymentDAOImpl {
    public static String getCurrentId() throws SQLException {
        String sql = "SELECT transaction_id FROM Transaction ORDER BY transaction_id DESC LIMIT 1";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    public static String getNextId(String currentPaymentId) {
        if (currentPaymentId != null){
            String[] split = currentPaymentId.split("T");
            int idNum = Integer.parseInt(split[1]);
            return "T"+ ++idNum;
        }
        return "T1";
    }

    public static boolean savePayment(Payment payment) throws SQLException {
        String sql = "INSERT INTO Transaction VALUES(?,?,?,?,?)";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,payment.getTransaction_id());
        pstm.setObject(2,payment.getService_center_id());
        pstm.setObject(3,payment.getAmount());
        pstm.setObject(4,payment.getType());
        pstm.setObject(5,payment.getDate());
        return pstm.executeUpdate() > 0;
    }

    public static String getId(Date date) throws SQLException {
        String sql = "SELECT transaction_id FROM Transaction tr JOIN Service s ON tr.date=s.date WHERE tr.date = ?";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,date);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return resultSet.getString("transaction_id");
        }
        return null;
    }

    public static boolean updatePayment(Payment payment) throws SQLException {
        String sql = "UPDATE Transaction SET service_center_id=?,amount = ?,type = ?,date = ? WHERE transaction_id = ?";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,payment.getService_center_id());
        pstm.setObject(2,payment.getAmount());
        pstm.setObject(3,payment.getType());
        pstm.setObject(4,payment.getDate());
        pstm.setObject(5,payment.getTransaction_id());
        return pstm.executeUpdate() > 0;
    }

    public static List<String> getProfit() throws SQLException {
        String sql ="SELECT amount FROM Transaction";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        List<String> amountList = FXCollections.observableArrayList();
        while (resultSet.next()){
            amountList.add(resultSet.getString("amount"));
        }
        return amountList;
    }
}
