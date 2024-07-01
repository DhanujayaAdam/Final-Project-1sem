package lk.ijse.finalProject.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO{
    public boolean add(T obj) throws SQLException;
    public boolean update(T obj) throws SQLException;
    public boolean delete(String id) throws SQLException;
    public String getId() throws SQLException;
    public String generateNewId(String id);
    public T getObject(String id) throws SQLException;
    public List<T> getAll() throws SQLException;

}
