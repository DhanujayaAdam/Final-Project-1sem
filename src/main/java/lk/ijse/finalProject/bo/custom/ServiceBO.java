package lk.ijse.finalProject.bo.custom;

import com.mysql.cj.ServerPreparedQuery;
import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dto.ServiceDTO;

import java.sql.SQLException;
import java.util.List;

public interface ServiceBO extends SuperBO {
    public boolean addService(ServiceDTO obj) throws SQLException;
    public boolean updateService(ServiceDTO obj) throws SQLException;
    public boolean deleteService(String id) throws SQLException;
    public String getServiceId() throws SQLException;
    public String generateNewServiceId(String id);
    public ServiceDTO getServiceObject(String id) throws SQLException;
    public List<ServiceDTO> getAllService() throws SQLException;
}
