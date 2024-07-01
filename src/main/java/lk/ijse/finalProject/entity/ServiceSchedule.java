package lk.ijse.finalProject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ServiceSchedule {
    private String id;
    private String serviceType;
    private String warning_distance;
}
