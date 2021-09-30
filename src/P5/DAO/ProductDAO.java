package P5.DAO;

import P4.Domain.Reiziger;
import P5.Domain.OVChipkaart;
import P5.Domain.Product;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {

    boolean save(Product product) throws SQLException;

    boolean update(Product product) throws SQLException;

    boolean delete(Product product) throws SQLException;

    List<Product> findByOVChipkaart(OVChipkaart ovChipkaart) throws SQLException;

    List<Product> findAll() throws SQLException;

}
