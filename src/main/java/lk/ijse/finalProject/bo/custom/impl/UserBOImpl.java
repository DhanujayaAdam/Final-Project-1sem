package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.BOFactory;
import lk.ijse.finalProject.bo.custom.UserBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.custom.UserDAO;
import lk.ijse.finalProject.dao.custom.impl.UserDAOImpl;
import lk.ijse.finalProject.dto.UserDTO;
import lk.ijse.finalProject.entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getInstance(DAOFactory.DaoType.USER);
    @Override
    public boolean addUser(UserDTO obj) throws SQLException {
        return userDAO.add(new User(obj.getId(),obj.getName(),obj.getPassword()));
    }

    @Override
    public boolean updateUser(UserDTO obj) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteUser(String id) throws SQLException {
        return false;
    }

    @Override
    public String getUserId() throws SQLException {
        return userDAO.getId();
    }

    @Override
    public String generateNewUserId(String id) {
        return null;
    }

    @Override
    public UserDTO getUserObject(String id) throws SQLException {
        return null;
    }

    @Override
    public List<UserDTO> getAllUser() throws SQLException {
        return null;
    }
    @Override
    public List<String> checkCredintial(String userName, String id) throws SQLException{
        return userDAO.checkCredintial(userName,id);
    }
}
