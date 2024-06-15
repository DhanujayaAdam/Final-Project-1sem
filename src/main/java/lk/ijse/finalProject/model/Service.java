package lk.ijse.finalProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Service {
    private String id;
    private String vehicleId;
    private String serviceType;
    private String description;
    private Date date;
    private String serviceCenterId;
}
