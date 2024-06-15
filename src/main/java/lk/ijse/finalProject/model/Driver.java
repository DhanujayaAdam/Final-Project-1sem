package lk.ijse.finalProject.model;

import lombok.*;
import java.sql.Blob;
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Driver {
    private String driver_id;
    private String firstName;
    private String lastname;
    private String address;
    private String nic;
    private String dob;
    private String vehicle_id;
    private String contact;
    private String email;
    private String pic;

    public Driver(String firstName, String lastName, String pic) {
        this.firstName = firstName;
        this.lastname = lastName;
        this.pic = pic;
    }

    public Driver(String id, String firstName, String lastName, String address, String dob, String nic, String tel, String email, String absolutePath) {
        this.driver_id = id;
        this.firstName = firstName;
        this.lastname = lastName;
        this.address = address;
        this.nic = nic;
        this.dob = dob;
        this.contact = tel;
        this.email = email;
        this.pic = absolutePath;
    }
}


