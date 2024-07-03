package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.BOFactory;
import lk.ijse.finalProject.bo.custom.VehicleToBeServicedBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.custom.VehicleToBeServicedDAO;
import lk.ijse.finalProject.dao.custom.impl.VehicleToBeServicedDAOImpl;
import lk.ijse.finalProject.dto.VehicleToBeServiceDTO;
import lk.ijse.finalProject.entity.VehicleToBeService;

import java.sql.SQLException;
import java.util.List;

public class VehicleTOBeServicedBOImpl implements VehicleToBeServicedBO {
    VehicleToBeServicedDAO vehicleToBeServicedDAO = (VehicleToBeServicedDAO) DAOFactory.getDaoFactory().getInstance(DAOFactory.DaoType.VEHICLE_TO_BE_SERVICED);

    @Override
    public boolean addReport(VehicleToBeServiceDTO obj) throws SQLException {
        return vehicleToBeServicedDAO.add(new VehicleToBeService(obj.getId(),obj.getVehicleId(),obj.getStatus(),obj.getAlert_distance()));
    }

    @Override
    public boolean updateReport(VehicleToBeServiceDTO obj) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteReport(String id) throws SQLException {
        return false;
    }

    @Override
    public String getReportId() throws SQLException {
        return null;
    }

    @Override
    public String generateNewId(String id) {
        return null;
    }

    @Override
    public VehicleToBeServiceDTO getReportObject(String id) throws SQLException {
        return null;
    }

    @Override
    public List<VehicleToBeServiceDTO> getAllReport() throws SQLException {
        return null;
    }

    @Override
    public double getCurrentDistance(String vehicleId) throws SQLException {
        return 0;
    }

    @Override
    public boolean clearDistance(String vehicleId) throws SQLException {
        return vehicleToBeServicedDAO.clearDistance(vehicleId);
    }

    @Override
    public boolean updateCurrentDistance(double distance, String vehicleId) throws SQLException {
        return vehicleToBeServicedDAO.updateCurrentDistance(distance,vehicleId);
    }
}
