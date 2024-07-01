package lk.ijse.finalProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class VehicleSave {
    private VehicleDTO vehicle;
    private VehicleToBeServiceDTO vehicleService;
    private VehicleToBeServiceDTO tyreReplacement;
}
