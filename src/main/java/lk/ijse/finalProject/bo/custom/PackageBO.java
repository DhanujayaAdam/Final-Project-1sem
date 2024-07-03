package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dto.PackageDTO;

import java.sql.SQLException;
import java.util.List;

public interface PackageBO extends SuperBO {
    public boolean addPackage(PackageDTO obj) throws SQLException;
    public boolean updatePackage(PackageDTO obj) throws SQLException;
    public boolean deletePackage(String id) throws SQLException;
    public String getPackageId() throws SQLException;
    public String generateNewPackageId(String id);
    public PackageDTO getPackageObject(String id) throws SQLException;
    public List<PackageDTO> getAllPackage() throws SQLException;
    public List<String> getPackageList() throws SQLException;
    public List<String> getTypeOfGood() throws SQLException;
    public String getTrackingNumber() throws SQLException;
    public String getVehicleId(String packageId) throws SQLException;
    public int getPackageCount1(String c1) throws SQLException;
    public List<String> getTrackingNumbers() throws SQLException;
    public List<String> getCompanyId() throws SQLException;
}
