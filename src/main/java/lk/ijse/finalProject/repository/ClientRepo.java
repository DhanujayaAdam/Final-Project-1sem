package lk.ijse.finalProject.repository;

import lk.ijse.finalProject.DB.Dbconnection;
import lk.ijse.finalProject.model.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientRepo {
    public static String getCurrentId() throws SQLException {
        String sql = "SELECT client_company_id FROM Client ORDER BY client_company_id DESC LIMIT 1";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
           return resultSet.getString(1);
        }
        return null;
    }

    public static String getAvailableId(String currentId) {
        if (currentId != null){
            String[] split = currentId.split("C");
            int idNum = Integer.parseInt(split[1]);
            return "C" + ++idNum;
        }
        return "C1";
    }

    public static boolean saveCompany(String availableId, String name, String address, String phone, String email) throws SQLException {
        String sql = "INSERT INTO Client VALUES(?, ?, ?, ?, ? )";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,availableId);
        pstm.setObject(2,name);
        pstm.setObject(3,address);
        pstm.setObject(4,phone);
        pstm.setObject(5,email);
        return pstm.executeUpdate() > 0;
    }

    public static Client getValues(String companyName) throws SQLException {
        String sql = "SELECT * FROM Client WHERE company_name = ?";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,companyName);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);
            String email = resultSet.getString(5);
            Client client = new Client(id,name,address,tel,email);
            return client;
        }
        return null;
    }

    public static boolean updateClient(String searchName, Client client) throws SQLException {
        String sql = "UPDATE Client SET company_name = ?,address = ?,contact_number = ?,email = ? WHERE company_name = ?";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,client.getName());
        pstm.setObject(2,client.getAddress());
        pstm.setObject(3,client.getTel());
        pstm.setObject(4,client.getEmail());
        pstm.setObject(5,searchName);
        return pstm.executeUpdate() > 0;
    }

    public static boolean deleteClient(String searchName) throws SQLException {
        String sql =  "DELETE Client WHERE company_name = ?";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,searchName);
        return pstm.executeUpdate() > 0;
    }

    public static List<Client> getAll() throws SQLException {
        String sql = "SELECT * FROM Client";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        List<Client> clientList = new ArrayList<>();
        while (resultSet.next()){
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String phone = resultSet.getString(4);
            String email = resultSet.getString(5);
            Client client = new Client(id,name,address,phone,email);
            clientList.add(client);
        }
        return clientList;
    }

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

    public static List<String> getNumber() throws SQLException {
        String sql = "SELECT contact_number FROM Client";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        List<String> numberList = new ArrayList<>();
        while (resultSet.next()){
            String contactNumber = resultSet.getString("contact_number");
            numberList.add(contactNumber);
        }
        return numberList;
    }

    public static List<String> getAllCompany() throws SQLException {
        String sql = "SELECT company_name FROM Client";
        PreparedStatement pstm = Dbconnection.
                getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        List<String> companyList = new ArrayList<>();
        while (resultSet.next()){
            String companyName = resultSet.getString("company_name");
            companyList.add(companyName);
        }
        return companyList;
    }

    public static List<String> getAddress() throws SQLException {
        String sql = "SELECT address FROM Client";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        List<String> adressList = new ArrayList<>();
        if (resultSet.next()){
            adressList.add(resultSet.getString("address"));
        }
        return adressList;
    }
}
