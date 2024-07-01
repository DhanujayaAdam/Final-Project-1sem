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
public class DriverDTO {
    private String driver_id;
    private String firstName;
    private String lastname;
    private String address;
    private String nic;
    private Date dob;
    private String vehicle_id;
    private String contact;
    private String email;
    private String pic;

    public DriverDTO(String firstName, String lastName, String pic) {
        this.firstName = firstName;
        this.lastname = lastName;
        this.pic = pic;
    }

    public DriverDTO(String id, String firstName, String lastName, String address, Date dob, String nic, String tel, String email, String absolutePath) {
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


