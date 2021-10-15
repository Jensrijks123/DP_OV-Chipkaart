package P5.DAO;

import P5.Domain.OVChipkaart;
import P5.Domain.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOPsql implements ProductDAO{

    private Connection connection;

    public ProductDAOPsql(Connection connection) throws SQLException {
        this.connection = connection;
    }

    ReizigerDAO reizigerDAO = new ReizigerDAOPsql(connection);
    OVChipkaartDAO ovChipkaartDAO = new OVChipkaartDAOPsql(connection);

    @Override
    public boolean save(Product product) throws SQLException {
        try {
            String save = "INSERT INTO product(product_nummer, naam, beschrijving, prijs)" + "VALUES (?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(save);
            pst.setInt(1, product.getId());
            pst.setString(2, product.getNaam());
            pst.setString(3, product.getBeschrijving());
            pst.setDouble(4, product.getPrijs());
            ResultSet r = pst.executeQuery();

            if (product.getOvChipkaartenNummers() != null) {
                for (Integer ov : product.getOvChipkaartenNummers()) {
                    product.addOvChipkaartNummer(ov);
                }
            }

            pst.close();
            r.close();

        } catch (SQLException e ){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Product product) throws SQLException {
        try {
            String update = "UPDATE product " + "SET product_nummer =?, naam =?, beschrijving =?, prijs =?" + "WHERE product_nummer =?";
            PreparedStatement pst = connection.prepareStatement(update);
            pst.setInt(1, product.getId());
            pst.setString(2, product.getNaam());
            pst.setString(3, product.getBeschrijving());
            pst.setDouble(4, product.getPrijs());
            pst.setInt(5, product.getId());
            ResultSet r = pst.executeQuery();

            if (product.getOvChipkaartenNummers() != null) {
                for (Integer ov : product.getOvChipkaartenNummers()) {
                    product.addOvChipkaartNummer(ov);
                }
            }

            pst.close();
            r.close();

        } catch (SQLException e ){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Product product) throws SQLException {
        try {
            String delete = "DELETE FROM product WHERE product_nummer =?";
            PreparedStatement pst = connection.prepareStatement(delete);
            pst.setInt(1, product.getId());
            ResultSet r = pst.executeQuery();

            if (product.getOvChipkaartenNummers() != null) {
                for (Integer ov : product.getOvChipkaartenNummers()) {
                    product.deleteOvChipkaartNummer(ov);
                }
            }

            pst.close();
            r.close();

        } catch (SQLException e ){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Product> findByOVChipkaart(OVChipkaart ovChipkaart) throws SQLException {

        List<Product> producten = new ArrayList<>();
        List<Integer> ovChipkaarts = new ArrayList<>();

        try {
            String findbyOVChipkaart = "SELECT p.product_nummer, p.naam, p.beschrijving, p.prijs, op.kaart_nummer, op.geldig_tot, op.klasse, op.saldo, op.reiziger_id FROM product p JOIN ov_chipkaart_product op ON p.product_nummer = op.product_nummer WHERE op.kaart_nummer =?";
            PreparedStatement pst = connection.prepareStatement(findbyOVChipkaart);
            pst.setInt(1, ovChipkaart.getKaartnummer());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Product product = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4));
                producten.add(product);
                for (Integer ov : ovChipkaarts) {
                    OVChipkaart ovChipkaart1 = new OVChipkaart(rs.getInt(5), rs.getDate(6), rs.getInt(7), rs.getDouble(8), reizigerDAO.findById(rs.getInt(9)));
                    ovChipkaarts.add(ovChipkaart1.getKaartnummer());
                    product.addOvChipkaartNummer(ovChipkaart1.getKaartnummer());
                }
            }
            pst.close();
            rs.close();

        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return producten;
    }

    @Override
    public List<Product> findAll() throws SQLException {

        List<Product> producten = new ArrayList<>();

        try {
            String findAll = "SELECT * FROM product";
            PreparedStatement pst = connection.prepareStatement(findAll);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Product product = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4));
                producten.add(product);

                if (product.getOvChipkaartenNummers() != null) {
                    for (Integer ov : product.getOvChipkaartenNummers()) {
                        product.addOvChipkaartNummer(ov);
                    }
                }
            }


            pst.close();
            rs.close();

        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return producten;
    }
}
