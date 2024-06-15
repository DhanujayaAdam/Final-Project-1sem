package lk.ijse.finalProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@NoArgsConstructor
@Data
@ToString
public class Vehicle {
    private String id;
    private String name;
    private String vehicle_number;
    private String chassis;
    private String engineNum;
    private String color;
    private String yom;
    private Date regDate;
    private double currentDistance;
    private String profile_picture;

    public Vehicle(String vehiId, String model, String vehicleNumber, String chassis, String engineNumber, String color, String yom, Date date, double distance, String rest) {
        this.id=vehiId;
        this.name = model;
        this.vehicle_number = vehicleNumber;
        this.chassis = chassis;
        this.engineNum = engineNumber;
        this.color = color;
        this.yom = yom;
        this.regDate = date;
        this.currentDistance = distance;
        this.profile_picture = rest;

    }
}