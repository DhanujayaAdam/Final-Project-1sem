package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.SqlUtil;
import lk.ijse.finalProject.dao.custom.ClientDAO;
import lk.ijse.finalProject.db.Dbconnection;
import lk.ijse.finalProject.entity.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {

    public static List<String> getCompanyId() throws SQLException {
        String sql = "SELECT client_company_id FROM Client";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        List<String> idList = new ArrayList<>();
        while(resultSet.next()){
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }

    public static List<String> getCompany() throws SQLException {
        String sql = "SELECT company_name FROM Client ORDER BY client_company_id DESC LIMIT 5";
        PreparedStatement pstm = Dbconnection.
                getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        List<String> companyList = new ArrayList<>();
        if (resultSet.next()){
            String companyName = resultSet.getString("company_name");
            companyList.add(companyName);
        }
        return companyList;
    }
    public static List<String> getAddress() throws SQLException {
        String sql = "SELECT address FROM Client ORDER BY client_company_id DESC LIMIT 4";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        List<String> adressList = new ArrayList<>();
        if (resultSet.next()){
            adressList.add(resultSet.getString("address"));
        }
        return adressList;
    }

    @Override
    public boolean add(Client obj) throws SQLException {
       return SqlUtil.execute("INSERT INTO Client VALUES(?, ?, ?, ?, ? )",obj.getCompany_id(),obj.getName(),obj.getAddress(),obj.getTel(),obj.getEmail());
    }

    @Override
    public boolean update(Client obj) throws SQLException {
        return SqlUtil.execute("UPDATE Client SET company_name = ?,address = ?,contact_number = ?,email = ? WHERE company_name = ?",obj.getName(),obj.getAddress(),obj.getTel(),obj.getEmail(),obj.getName());
    }

    @Override
    public boolean delete(String name) throws SQLException {
        return SqlUtil.execute("DELETE FROM Client WHERE company_name = ?",name);
    }

    @Override
    public String getId() throws SQLException {
        ResultSet resultSet = SqlUtil.execute("SELECT client_company_id FROM Client ORDER BY client_company_id DESC LIMIT 1");
        resultSet.next();
        return resultSet.getString("client_company_id");
    }

    @Override
    public String generateNewId(String id) {
        return null;
    }

    @Override
    public Client getObject(String id) {
        return null;
    }

    @Override
    public List<Client> getAll() throws SQLException {
        ResultSet resultSet =  SqlUtil.execute("SELECT * FROM Client");
        ArrayList<Client> clients = new ArrayList<>();
        while (resultSet.next()){
            clients.add( new Client(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return clients;
    }

    public Client getValues(String companyName) throws SQLException {
        ResultSet resultSet = SqlUtil.execute("SELECT * FROM Client WHERE company_name = ?",companyName);
        if (resultSet.next()){
            return new Client(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
                    );
        }
        return null;
    }

    @Override
    public List<String> getPhoneNumber() throws SQLException {
       ResultSet resultSet = SqlUtil.execute("SELECT contact_number FROM Client");
       ArrayList<String> number = new ArrayList<>();
       while (resultSet.next()){
           number.add(resultSet.getString("contact_number"));
       }
       return number;
    }

    @Override
    public List<String> getName() throws SQLException {
       ResultSet resultSet = SqlUtil.execute("SELECT company_name FROM Client");
       ArrayList<String> name = new ArrayList<>();
       while (resultSet.next()){
           name.add(resultSet.getString("company_name"));
       }
       return name;
    }
}
