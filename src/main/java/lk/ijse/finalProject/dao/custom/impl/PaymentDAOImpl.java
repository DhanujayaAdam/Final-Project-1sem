package lk.ijse.finalProject.dao.custom.impl;

import javafx.collections.FXCollections;
import lk.ijse.finalProject.dao.SqlUtil;
import lk.ijse.finalProject.dao.custom.PackageDAO;
import lk.ijse.finalProject.dao.custom.PaymentDAO;
import lk.ijse.finalProject.db.Dbconnection;
import lk.ijse.finalProject.entity.Package;
import lk.ijse.finalProject.entity.Payment;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public boolean add(Payment obj) throws SQLException {
        return SqlUtil.execute("INSERT INTO Transaction VALUES(?,?,?,?,?)",obj.getTransaction_id(),obj.getService_center_id(),obj.getAmount(),obj.getType(),obj.getDate());
    }

    @Override
    public boolean update(Payment obj) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public String getId() throws SQLException {
        ResultSet rst = SqlUtil.execute("SELECT transaction_id FROM Transaction ORDER BY transaction_id DESC LIMIT 1");
        if (rst.next())
            return rst.getString("transaction_id");
        return null;
    }

    @Override
    public String generateNewId(String id) {
        return null;
    }

    @Override
    public Payment getObject(String id) throws SQLException {
        return null;
    }

    @Override
    public List<Payment> getAll() throws SQLException {
        return null;
    }
}
