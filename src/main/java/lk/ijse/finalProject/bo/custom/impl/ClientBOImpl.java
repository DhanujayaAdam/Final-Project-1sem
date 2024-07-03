package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.BOFactory;
import lk.ijse.finalProject.bo.custom.ClientBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.custom.ClientDAO;
import lk.ijse.finalProject.dao.custom.impl.ClientDAOImpl;
import lk.ijse.finalProject.dto.ClientDTO;
import lk.ijse.finalProject.entity.Client;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientBOImpl implements ClientBO {
    ClientDAO clientDAO = (ClientDAO) DAOFactory.getDaoFactory().getInstance(DAOFactory.DaoType.CLIENT);
    @Override
    public boolean addCompany(ClientDTO obj) throws SQLException {
        return clientDAO.add(new Client(obj.getCompany_id(), obj.getName(), obj.getAddress(), obj.getTel(),obj.getEmail()));
    }

    @Override
    public boolean updateCompany(ClientDTO obj) throws SQLException {
        return clientDAO.update(new Client(obj.getCompany_id(), obj.getName(), obj.getAddress(), obj.getTel(), obj.getEmail()));
    }

    @Override
    public boolean deleteCompany(String name) throws SQLException {
        return clientDAO.delete(name);
    }

    @Override
    public String getCompanyId() throws SQLException {
        return clientDAO.getId();
    }

    @Override
    public ClientDTO getCompanyObject(String id) {
        return null;
    }

    @Override
    public List<ClientDTO> getAllCompany() throws SQLException {
        List<Client> clients = clientDAO.getAll();
        ArrayList<ClientDTO> clientDTOS = new ArrayList<>();
        for (Client clientDTO : clients){
            clientDTOS.add(new ClientDTO(
                    clientDTO.getCompany_id(),
                    clientDTO.getName(),
                    clientDTO.getAddress(),
                    clientDTO.getTel(),
                    clientDTO.getEmail()
            ));
        }
        return clientDTOS;
    }

    @Override
    public ClientDTO getCompanyValues(String companyName) throws SQLException {
        Client client = clientDAO.getValues(companyName);
        return new ClientDTO(client.getCompany_id(),client.getName(),client.getAddress(),client.getTel(),client.getEmail());
    }

    @Override
    public List<String> getCompanyName() throws SQLException {
        return clientDAO.getName();
    }

    @Override
    public List<String> getNumber() throws SQLException {
        return clientDAO.getPhoneNumber();
    }

    @Override
    public List<String> getCompanyIdList() throws SQLException {
        return clientDAO.getIdList();
    }

    @Override
    public List<String> getCompany() throws SQLException {
        return clientDAO.getCompany();
    }

    @Override
    public List<String> getAddress() throws SQLException {
        return clientDAO.getAddress();
    }
}
