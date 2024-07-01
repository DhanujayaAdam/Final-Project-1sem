package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dao.SuperDAO;
import lk.ijse.finalProject.dto.ClientDTO;
import lk.ijse.finalProject.entity.Client;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ClientBO extends SuperBO {
    public boolean addCompany(ClientDTO obj) throws SQLException;
    public boolean updateCompany(ClientDTO obj) throws SQLException;
    public boolean deleteCompany(String id) throws SQLException;
    public String getCompanyId() throws SQLException;
    public ClientDTO getCompanyObject(String id);
    public List<ClientDTO> getAllCompany() throws SQLException;
    public ClientDTO getCompanyValues(String companyName) throws SQLException;
    public List<String> getCompanyName() throws SQLException;
    public List<String> getNumber() throws SQLException;
}
