package P5.DAO;

import P5.Domain.OVChipkaart;
import P5.Domain.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductDAOPsql implements ProductDAO{

    private Connection connection;

    public ProductDAOPsql(Connection connection) throws SQLException {
        this.connection = connection;
    }

//    ReizigerDAO reizigerDAO = new ReizigerDAOPsql(connection);

    @Override
    public boolean save(Product product) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Product product) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Product product) throws SQLException {
        return false;
    }

    @Override
    public OVChipkaart findByOVChipkaart(OVChipkaart ovChipkaart) throws SQLException {
        return null;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        return null;
    }
}
