package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.BOFactory;
import lk.ijse.finalProject.bo.custom.VehicleBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.custom.VehicleDAO;
import lk.ijse.finalProject.dao.custom.impl.VehicleDAOImpl;
import lk.ijse.finalProject.dto.VehicleDTO;
import lk.ijse.finalProject.entity.Vehicle;

import java.sql.SQLException;
import java.util.List;

public class VehicleBOImpl implements VehicleBO {
    VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getDaoFactory().getInstance(DAOFactory.DaoType.VEHICLE);
    @Override
    public boolean saveVehicle(VehicleDTO vehicle) throws SQLException {
        System.out.println(vehicle);
        return vehicleDAO.add(new Vehicle(vehicle.getId(),vehicle.getName(),vehicle.getVehicle_number(),vehicle.getChassis(),vehicle.getEngineNum(),vehicle.getColor(),vehicle.getYom(),vehicle.getRegDate(),vehicle.getCurrentDistance(),vehicle.getProfile_picture()));
    }

    @Override
    public boolean updateVehicle(VehicleDTO vehicle) throws SQLException {
        return vehicleDAO.update(new Vehicle(vehicle.getId(),vehicle.getName(),vehicle.getVehicle_number(),vehicle.getChassis(),vehicle.getEngineNum(),vehicle.getColor(),vehicle.getYom(),vehicle.getRegDate(),vehicle.getCurrentDistance(),vehicle.getProfile_picture()));
    }

    @Override
    public boolean deleteVehicle(String id) throws SQLException {
        return vehicleDAO.delete(id);
    }

    @Override
    public String getVehicleId() throws SQLException {
        return vehicleDAO.getId();
    }

    @Override
    public VehicleDTO getVehicleObject(String id) throws SQLException {
        Vehicle vehicle = vehicleDAO.getObject(id);
        System.out.println(vehicle);
        return new VehicleDTO(vehicle.getId(),vehicle.getName(),vehicle.getVehicle_number(),vehicle.getChassis(),vehicle.getEngineNum(),vehicle.getColor(),vehicle.getYom(),vehicle.getRegDate(), vehicle.getCurrentDistance(),vehicle.getProfile_picture());
    }

    @Override
    public List<VehicleDTO> getAll() throws SQLException {
        return null;
    }

    @Override
    public List<String> vehicleIdList() throws SQLException {
        return vehicleDAO.getVehicleId();
    }

    @Override
    public List<String> getCurrentVehicleList() throws SQLException {
        return vehicleDAO.getCurrentVehicleList();
    }

    @Override
    public List<String> getVehicleNameList() throws SQLException {
        return vehicleDAO.getVehicleNameList();
    }

    @Override
    public List<String> getVehicleNumber() throws SQLException {
        return vehicleDAO.getVehicleNumber();
    }

    @Override
    public boolean updateVehicleDistance(String vehicleId, double distance) throws SQLException {
        return vehicleDAO.updateVehicleDistance(vehicleId,distance);
    }

    @Override
    public double getCurrentDistance(String vehicleId) throws SQLException {
        return vehicleDAO.getCurrentDistance(vehicleId);
    }


}
