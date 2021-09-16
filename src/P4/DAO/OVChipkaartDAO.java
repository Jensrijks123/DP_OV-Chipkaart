package P4.DAO;

import P4.Domain.OVChipkaart;
import P4.Domain.Reiziger;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public interface OVChipkaartDAO {
    List<OVChipkaart> findByReiziger(Reiziger reiziger) throws SQLException;
}
