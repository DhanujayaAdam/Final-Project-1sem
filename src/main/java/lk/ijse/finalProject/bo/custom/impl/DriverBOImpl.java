package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.custom.DriverBO;
import lk.ijse.finalProject.dao.custom.DriverDAO;
import lk.ijse.finalProject.dao.custom.impl.DriverDAOImpl;
import lk.ijse.finalProject.dto.DriverDTO;
import lk.ijse.finalProject.entity.Driver;

import java.sql.SQLException;
import java.util.List;

public class DriverBOImpl implements DriverBO {
    DriverDAO driverDAO = new DriverDAOImpl();

    @Override
    public boolean addDriver(DriverDTO driver) throws SQLException {
        return driverDAO.add(new Driver(driver.getDriver_id(), driver.getFirstName(), driver.getLastname(),driver.getAddress(),driver.getNic(),driver.getDob(),driver.getVehicle_id(),driver.getContact(),driver.getEmail(),driver.getPic()));
    }

    @Override
    public boolean updateDriver(DriverDTO driver) throws SQLException {
        return driverDAO.update(new Driver(driver.getDriver_id(),driver.getFirstName(),driver.getLastname(),driver.getAddress(),driver.getDob(),driver.getNic(),driver.getContact(),driver.getEmail(),driver.getPic()));
    }

    @Override
    public boolean deleteDriver(String id) throws SQLException {
        return driverDAO.delete(id);
    }

    @Override
    public String getId() throws SQLException {
        return driverDAO.getId();
    }

    @Override
    public String generateNewId(String id) {
        return null;
    }

    @Override
    public DriverDTO getDriver(String id) throws SQLException {
        Driver driver = driverDAO.getObject(id);
        return new DriverDTO(driver.getDriver_id(),driver.getFirstName(),driver.getLastname(),driver.getAddress(),driver.getNic(), driver.getDob(), driver.getVehicle_id(),driver.getContact(),driver.getEmail(),driver.getPic());
    }

    @Override
    public List<DriverDTO> getAll() throws SQLException {
        return null;
    }
    @Override
    public List<String> getDriverId() throws SQLException {
        return driverDAO.driverId();
    }

    @Override
    public List<String> getName() throws SQLException {
        return driverDAO.getDriverName();
    }
}
