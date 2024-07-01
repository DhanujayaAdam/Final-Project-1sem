package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.SqlUtil;
import lk.ijse.finalProject.dao.custom.UserDAO;
import lk.ijse.finalProject.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean add(User obj) throws SQLException {
        return SqlUtil.execute("INSERT INTO User (user_id,user_name,password) VALUES (?,?,?)",obj.getId(),obj.getName(),obj.getPassword());
    }

    @Override
    public boolean update(User obj) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public String getId() throws SQLException {
        ResultSet resultSet = SqlUtil.execute("SELECT user_id FROM User ORDER BY user_id DESC LIMIT 1");
        resultSet.next();
        return resultSet.getString("user_id");
    }

    @Override
    public String generateNewId(String id) {
        return null;
    }

    @Override
    public User getObject(String id) throws SQLException {
        return null;
    }

    @Override
    public List<User> getAll() throws SQLException {
        return null;
    }

    @Override
    public List<String > checkCredintial(String userName,String id) throws SQLException {
        ResultSet rst = SqlUtil.execute("SELECT user_name,password FROM User WHERE user_name = ? || user_id = ?",userName,id);
        ArrayList<String> dbValues = new ArrayList<>();
        rst.next();
        dbValues.add(rst.getString("user_name"));
        dbValues.add(rst.getString("password"));
        return dbValues;
    }
}
