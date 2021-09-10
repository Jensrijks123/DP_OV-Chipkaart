package P3;

import P2.Reiziger;

import java.sql.SQLException;
import java.util.List;

public interface ReizigerDAO {

    boolean save(P2.Reiziger reiziger) throws SQLException;

    boolean update(P2.Reiziger reiziger);

    boolean delete(P2.Reiziger reiziger);

    P2.Reiziger findById(int id);

    List<P2.Reiziger> findByGbdatum(String datum);

    List<Reiziger> findAll() throws SQLException;
}
