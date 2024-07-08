package view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PackageTm {
    private String orderId;
    private String trackingNumber;
    private String companyId;
    private String typeOfGood;
    private double weight;
    private String deliveryType;
    private Date borrowDAte;
    private String ShipmentId;
    private double price;
}
