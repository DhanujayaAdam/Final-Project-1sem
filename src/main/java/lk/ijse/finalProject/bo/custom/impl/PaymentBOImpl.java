package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.BOFactory;
import lk.ijse.finalProject.bo.custom.PaymentBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.custom.PaymentDAO;
import lk.ijse.finalProject.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.finalProject.dto.PaymentDTO;
import lk.ijse.finalProject.entity.Payment;

import java.sql.SQLException;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getInstance(DAOFactory.DaoType.PAYMENT);
    @Override
    public boolean addPayment(PaymentDTO obj) throws SQLException {
        return paymentDAO.add(new Payment(obj.getTransaction_id(), obj.getService_center_id(), obj.getAmount(), obj.getType(), obj.getDate()));
    }

    @Override
    public boolean updatePayment(PaymentDTO obj) throws SQLException {
        return false;
    }

    @Override
    public boolean deletePayment(String id) throws SQLException {
        return false;
    }

    @Override
    public String getPaymentId() throws SQLException {
        return paymentDAO.getId();
    }

    @Override
    public String generateNewPaymentId(String id) {
        return null;
    }

    @Override
    public PaymentDTO getPaymentObject(String id) throws SQLException {
        return null;
    }

    @Override
    public List<PaymentDTO> getAllPayment() throws SQLException {
        return null;
    }
}
