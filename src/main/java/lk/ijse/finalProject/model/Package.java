package lk.ijse.finalProject.model;

import lk.ijse.finalProject.DB.Dbconnection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Package {
    private String orderId;
    private String trackingNumber;
    private String companyId;
    private String typeOfGood;
    private double weight;
    private String deliveryType;
    private Date borrowDAte;
    private String ShipmentId;
}
