package lk.ijse.finalProject.model.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleTm {
    private String id;
    private String name;
    private String chassis;
    private String engineNum;
    private String color;
    private String yom;
    private String regDate;
    private String currentDistance;
}
