package lk.ijse.finalProject.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnection {
    private static Dbconnection dbconnection;
    private Connection connection;

    private Dbconnection() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/us_cargo_freight_service",
                "root",
                "Ijse@123"
        );
    }
    public static Dbconnection getInstance() throws SQLException {
        if (dbconnection == null){
            dbconnection = new Dbconnection();
            return dbconnection;
        } else {
            return dbconnection;
        }
    }
    public Connection getConnection(){
        return connection;
    }
}
