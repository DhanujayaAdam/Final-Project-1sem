package lk.ijse.finalProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class VehicleToBeServiceDTO {
    private String id;
    private String vehicleId;
    private String status;
    private double alert_distance;
}
