package lk.ijse.finalProject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Client {
    private String company_id;
    private String name;
    private String address;
    private String tel;
    private String email;

    public Client(String name, String address, String phone, String email) {
        this.name = name;
        this.address = address;
        this.tel = phone;
        this.email = email;
    }
}
