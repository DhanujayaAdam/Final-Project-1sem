package lk.ijse.finalProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class DeliveryDetailDTO {
    private String shipmentId;
    private String vehicleId;
    private String destination;
}
