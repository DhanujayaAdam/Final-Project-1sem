package lk.ijse.finalProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class PackageDTO {
    private String orderId;
    private String trackingNumber;
    private String companyId;
    private String typeOfGood;
    private double weight;
    private String deliveryType;
    private Date borrowDAte;
    private String ShipmentId;
}
