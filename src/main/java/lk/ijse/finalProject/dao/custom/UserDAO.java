package lk.ijse.finalProject.dao.custom;

import lk.ijse.finalProject.dao.CrudDAO;
import lk.ijse.finalProject.entity.User;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO extends CrudDAO<User> {
    public List<String> checkCredintial(String userName, String id) throws SQLException;
}
