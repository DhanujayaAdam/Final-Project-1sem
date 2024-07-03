package lk.ijse.finalProject.dao.custom;

import lk.ijse.finalProject.dao.CrudDAO;
import lk.ijse.finalProject.entity.Package;

import java.sql.SQLException;
import java.util.List;

public interface PackageDAO extends CrudDAO<Package> {
    public List<String> getPackageList() throws SQLException;
    public List<String> getTypeOfGood() throws SQLException;
    public String getTrackingNumber() throws SQLException;
    public String getVehicleId(String packageId) throws SQLException;
    public int getPackageCount1(String c1) throws SQLException;
    public List<String> getTrackingNumbers() throws SQLException;
    public List<String> getCompanyId() throws SQLException;
}
