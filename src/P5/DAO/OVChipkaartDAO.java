package P5.DAO;

import P4.Domain.OVChipkaart;
import P4.Domain.Reiziger;

import java.sql.SQLException;
import java.util.List;

public interface OVChipkaartDAO {

    List<OVChipkaart> findByReiziger(Reiziger reiziger) throws SQLException;

    boolean save(OVChipkaart ovChipkaart) throws SQLException;

    boolean update(OVChipkaart ovChipkaart) throws SQLException;

    boolean delete(OVChipkaart ovChipkaart) throws SQLException;

    List<OVChipkaart> findAll() throws SQLException;
}
