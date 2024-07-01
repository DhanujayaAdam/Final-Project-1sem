package lk.ijse.finalProject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class PackageSave {
    private Package aPackage;
    private Shipment shipment;
    private DeliveryDetail deliveryDetail;
    private Route route;
    private String vehicleId;

}
