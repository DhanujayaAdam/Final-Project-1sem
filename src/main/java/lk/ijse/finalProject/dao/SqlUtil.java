package lk.ijse.finalProject.dao;

import lk.ijse.finalProject.db.Dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlUtil {
    public static <T>T execute(String sql , Object... obj) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        int index = 1;
        for (Object object : obj){
            pstm.setObject(index,object);
            ++index;
        }
        if (sql.startsWith("SELECT")){
            return (T) pstm.executeQuery();
        } else {
            return (T)(Boolean)(pstm.executeUpdate() > 0);
        }
    }
}
