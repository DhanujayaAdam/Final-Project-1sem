package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.BOFactory;
import lk.ijse.finalProject.bo.custom.ServiceBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.custom.ServiceDAO;
import lk.ijse.finalProject.dao.custom.impl.ServiceDAOImpl;
import lk.ijse.finalProject.dto.ServiceDTO;
import lk.ijse.finalProject.entity.Service;

import java.sql.SQLException;
import java.util.List;

public class ServiceBOImpl implements ServiceBO {
    ServiceDAO serviceDAO = (ServiceDAO) DAOFactory.getDaoFactory().getInstance(DAOFactory.DaoType.SERVICE);

    @Override
    public boolean addService(ServiceDTO obj) throws SQLException {
        return serviceDAO.add(new Service(obj.getId(), obj.getVehicleId(), obj.getServiceType(), obj.getDescription(), obj.getDate(),obj.getServiceCenterId()));
    }

    @Override
    public boolean updateService(ServiceDTO obj) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteService(String id) throws SQLException {
        return false;
    }

    @Override
    public String getServiceId() throws SQLException {
        return serviceDAO.getId();
    }

    @Override
    public String generateNewServiceId(String id) {
        return null;
    }

    @Override
    public ServiceDTO getServiceObject(String id) throws SQLException {
        Service service = serviceDAO.getObject(id);
        return new ServiceDTO(service.getId(),service.getVehicleId(),service.getServiceType(),service.getDescription(),service.getDate(),service.getServiceCenterId());
    }

    @Override
    public List<ServiceDTO> getAllService() throws SQLException {
        return null;
    }

}
