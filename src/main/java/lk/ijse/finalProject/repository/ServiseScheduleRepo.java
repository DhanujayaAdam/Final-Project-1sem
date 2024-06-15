package lk.ijse.finalProject.repository;

import lk.ijse.finalProject.DB.Dbconnection;

import java.awt.image.DataBuffer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiseScheduleRepo {
    public static String getShchedule1(String vehicleService) throws SQLException {
        String sql = "SELECT service_schedule_id FROM Service_schedule WHERE service_type = ?";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,vehicleService);
        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    public static List<String> getType() throws SQLException {
        String sql = "SELECT service_type FROM Service_schedule";
        PreparedStatement pstm = Dbconnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        List<String> typeList = new ArrayList<>();
        while (resultSet.next()){
            typeList.add(resultSet.getString("service_type"));
        }
        return typeList;
    }
}
