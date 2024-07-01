package lk.ijse.finalProject.entity.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientTm {
    private String id;
    private String name;
    private String address;
    private String tel;
    private String email;
}
