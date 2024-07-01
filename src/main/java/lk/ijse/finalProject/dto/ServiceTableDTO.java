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
public class ServiceTableDTO {
    private String vehicleId;
    private String description;
    private Date date;
    private String centerId;
    private double amount;
}
