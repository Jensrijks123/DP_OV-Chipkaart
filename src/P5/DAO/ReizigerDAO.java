package P5.DAO;

import P4.Domain.Reiziger;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface ReizigerDAO {

    boolean save(Reiziger reiziger) throws SQLException;

    boolean update(Reiziger reiziger) throws SQLException;

    boolean delete(Reiziger reiziger) throws SQLException;

    Reiziger findById(int id) throws SQLException;

    List<Reiziger> findByGbdatum(Date datum) throws SQLException;

    List<Reiziger> findAll() throws SQLException;
}
