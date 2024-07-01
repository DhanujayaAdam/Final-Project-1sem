package lk.ijse.finalProject.dao.custom;

import lk.ijse.finalProject.dao.CrudDAO;
import lk.ijse.finalProject.db.Dbconnection;
import lk.ijse.finalProject.entity.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ClientDAO extends CrudDAO<Client> {
    public Client getValues(String companyName) throws SQLException;
    public List<String> getPhoneNumber() throws SQLException;
    public List<String> getName() throws SQLException;

}
