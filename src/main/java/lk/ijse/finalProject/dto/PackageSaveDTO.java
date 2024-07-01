package lk.ijse.finalProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class PackageSaveDTO {
    private PackageDTO aPackage;
    private ShipmentDTO shipment;
    private DeliveryDetailDTO deliveryDetail;
    private RouteDTO route;
    private String vehicleId;

}
