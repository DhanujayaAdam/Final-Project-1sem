package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dao.SuperDAO;
import lk.ijse.finalProject.dto.UserDTO;

import java.sql.SQLException;
import java.util.List;

public interface UserBO extends SuperBO {
    public boolean addUser(UserDTO obj) throws SQLException;
    public boolean updateUser(UserDTO obj) throws SQLException;
    public boolean deleteUser(String id) throws SQLException;
    public String getUserId() throws SQLException;
    public String generateNewUserId(String id);
    public UserDTO getUserObject(String id) throws SQLException;
    public List<UserDTO> getAllUser() throws SQLException;
    public List<String> checkCredintial(String userName,String id) throws SQLException;
}
