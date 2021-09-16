package P4.DAO;

import P4.Domain.OVChipkaart;
import P4.Domain.Reiziger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OVChipkaartDAOPsql implements OVChipkaartDAO{

    private Connection connection;

//    AdresDAO adresDAO = new AdresDAOPsql(connection);

    public OVChipkaartDAOPsql(Connection connection) throws SQLException {
        this.connection = connection;
    }

    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) throws SQLException {
        return null;
    }
}
