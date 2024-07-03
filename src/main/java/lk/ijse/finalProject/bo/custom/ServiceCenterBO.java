package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dto.ServiceCenterDTO;
import lk.ijse.finalProject.entity.ServiceCenter;

import java.sql.SQLException;
import java.util.List;

public interface ServiceCenterBO extends SuperBO {
    public boolean addCenter(ServiceCenterDTO obj) throws SQLException;
    public boolean updateCenter(ServiceCenterDTO obj) throws SQLException;
    public boolean deleteCenter(String id) throws SQLException;
    public String getCenterId() throws SQLException;
    public String generateNewCenterId(String id);
    public ServiceCenterDTO getCenterObject(String id) throws SQLException;
    public List<ServiceCenterDTO> getAllCenter() throws SQLException;
    public List<String> getName() throws SQLException;
    public List<String> getPhone() throws SQLException;
    public List<String> getIds() throws SQLException;
}
