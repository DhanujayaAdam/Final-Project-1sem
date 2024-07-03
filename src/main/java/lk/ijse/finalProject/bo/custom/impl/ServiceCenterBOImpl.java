package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.BOFactory;
import lk.ijse.finalProject.bo.custom.ServiceCenterBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.custom.ServiceCenterDAO;
import lk.ijse.finalProject.dao.custom.impl.ServiceCenterDAOImpl;
import lk.ijse.finalProject.dto.ServiceCenterDTO;
import lk.ijse.finalProject.entity.ServiceCenter;

import java.sql.SQLException;
import java.util.List;

public class ServiceCenterBOImpl implements ServiceCenterBO {
    ServiceCenterDAO serviceCenterDAO = (ServiceCenterDAO) DAOFactory.getDaoFactory().getInstance(DAOFactory.DaoType.SERVICE_CENTER);

    @Override
    public boolean addCenter(ServiceCenterDTO obj) throws SQLException {
        return serviceCenterDAO.add(new ServiceCenter(obj.getId(), obj.getName(), obj.getLocation(), obj.getTel(), obj.getEmail()));
    }

    @Override
    public boolean updateCenter(ServiceCenterDTO obj) throws SQLException {
        return serviceCenterDAO.update(new ServiceCenter(obj.getId(), obj.getName(), obj.getLocation(), obj.getTel(), obj.getEmail()));
    }

    @Override
    public boolean deleteCenter(String id) throws SQLException {
        return false;
    }

    @Override
    public String getCenterId() throws SQLException {
        return serviceCenterDAO.getId();
    }

    @Override
    public String generateNewCenterId(String id) {
        return null;
    }

    @Override
    public ServiceCenterDTO getCenterObject(String id) throws SQLException {
        ServiceCenter serviceCenter = serviceCenterDAO.getObject(id);
        return new ServiceCenterDTO(serviceCenter.getId(), serviceCenter.getName(), serviceCenter.getLocation(), serviceCenter.getTel(), serviceCenter.getEmail());
    }

    @Override
    public List<ServiceCenterDTO> getAllCenter() throws SQLException {
        return null;
    }

    @Override
    public List<String> getName() throws SQLException {
        return serviceCenterDAO.getName();
    }

    @Override
    public List<String> getPhone() throws SQLException {
        return serviceCenterDAO.getPhone();
    }

    @Override
    public List<String> getIds() throws SQLException {
        return serviceCenterDAO.getIds();
    }
}
