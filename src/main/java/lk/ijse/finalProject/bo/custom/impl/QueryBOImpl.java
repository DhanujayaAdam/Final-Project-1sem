package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.BOFactory;
import lk.ijse.finalProject.bo.custom.QueryBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.custom.QueryDAO;
import lk.ijse.finalProject.dao.custom.impl.QueryDAOImpl;
import lk.ijse.finalProject.dto.ServiceTableDTO;
import lk.ijse.finalProject.entity.ServiceTable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryBOImpl implements QueryBO {
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getInstance(DAOFactory.DaoType.JOIN_QUERY);
    @Override
    public List<ServiceTableDTO> getAllDetail() throws SQLException {
        List<ServiceTable> list = queryDAO.getAllServiceDetail();
        List<ServiceTableDTO> tableDTOList = new ArrayList<>();
        for (ServiceTable serviceTableDTO : list){
            tableDTOList.add(new ServiceTableDTO(
                    serviceTableDTO.getVehicleId(),
                    serviceTableDTO.getDescription(),
                    serviceTableDTO.getDate(),
                    serviceTableDTO.getCenterId(),
                    serviceTableDTO.getAmount()
            ));
        }
        return tableDTOList;
    }
}
