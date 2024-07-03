package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.BOFactory;
import lk.ijse.finalProject.bo.custom.PackageBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.custom.PackageDAO;
import lk.ijse.finalProject.dao.custom.impl.PackageDAOImpl;
import lk.ijse.finalProject.dto.PackageDTO;
import lk.ijse.finalProject.entity.Package;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PackageBOImpl implements PackageBO {
    PackageDAO packageDAO = (PackageDAO) DAOFactory.getDaoFactory().getInstance(DAOFactory.DaoType.PACKAGE);
    @Override
    public boolean addPackage(PackageDTO obj) throws SQLException {
        System.out.println(obj);
        return packageDAO.add(new Package(obj.getOrderId(),obj.getTrackingNumber(),obj.getCompanyId(),obj.getTypeOfGood(),obj.getWeight(),obj.getDeliveryType(),obj.getBorrowDAte(),obj.getShipmentId()));
    }

    @Override
    public boolean updatePackage(PackageDTO obj) throws SQLException {
        return false;
    }

    @Override
    public boolean deletePackage(String id) throws SQLException {
        return false;
    }

    @Override
    public String getPackageId() throws SQLException {
        return packageDAO.getId();
    }

    @Override
    public String generateNewPackageId(String id) {
        return null;
    }

    @Override
    public PackageDTO getPackageObject(String id) throws SQLException {
        Package packages = packageDAO.getObject(id);
        return new PackageDTO(packages.getOrderId(),packages.getTrackingNumber(),packages.getCompanyId(),packages.getTypeOfGood(),packages.getWeight(),packages.getDeliveryType(),packages.getBorrowDAte(),packages.getShipmentId());
    }

    @Override
    public List<PackageDTO> getAllPackage() throws SQLException {
        List<Package> packageList = packageDAO.getAll();
        List<PackageDTO> packageDTOS = new ArrayList<>();
        for (Package packages : packageList){
            packageDTOS.add(new PackageDTO(packages.getOrderId(),packages.getTrackingNumber(),packages.getCompanyId(),packages.getTypeOfGood(),packages.getWeight(),packages.getDeliveryType(),packages.getBorrowDAte(),packages.getShipmentId()));
        }
        return packageDTOS;
    }

    @Override
    public List<String> getPackageList() throws SQLException {
        return packageDAO.getPackageList();
    }

    @Override
    public List<String> getTypeOfGood() throws SQLException {
        return packageDAO.getTypeOfGood();
    }

    @Override
    public String getTrackingNumber() throws SQLException {
        return packageDAO.getTrackingNumber();
    }

    @Override
    public String getVehicleId(String packageId) throws SQLException {
        return packageDAO.getVehicleId(packageId);
    }

    @Override
    public int getPackageCount1(String c1) throws SQLException {
        return packageDAO.getPackageCount1(c1);
    }

    @Override
    public List<String> getTrackingNumbers() throws SQLException {
        return packageDAO.getTrackingNumbers();
    }

    @Override
    public List<String> getCompanyId() throws SQLException {
        return packageDAO.getCompanyId();
    }
}
