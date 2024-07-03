package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dto.PaymentDTO;

import java.sql.SQLException;
import java.util.List;

public interface PaymentBO extends SuperBO {
    public boolean addPayment(PaymentDTO obj) throws SQLException;
    public boolean updatePayment(PaymentDTO obj) throws SQLException;
    public boolean deletePayment(String id) throws SQLException;
    public String getPaymentId() throws SQLException;
    public String generateNewPaymentId(String id);
    public PaymentDTO getPaymentObject(String id) throws SQLException;
    public List<PaymentDTO> getAllPayment() throws SQLException;
}
