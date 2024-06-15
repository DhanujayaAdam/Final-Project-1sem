package lk.ijse.finalProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class VehicleSave {
    private Vehicle vehicle;
    private VehicleToBeService vehicleService;
    private VehicleToBeService tyreReplacement;
}
