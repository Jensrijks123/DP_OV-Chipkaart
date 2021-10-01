package P5.DAO;

import P5.Domain.OVChipkaart;
import P5.Domain.Product;
import P5.Domain.Reiziger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartDAOPsql implements OVChipkaartDAO {

    private Connection connection;

    public OVChipkaartDAOPsql(Connection connection) throws SQLException {
        this.connection = connection;
    }

    ReizigerDAO reizigerDAO = new ReizigerDAOPsql(connection);
    ProductDAO productDAO = new ProductDAOPsql(connection);

    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) throws SQLException {

        List<OVChipkaart> ovChipkaartArrayList = new ArrayList<>();
        List<Product> producten = productDAO.findAll();

        try {
            String findByReiziger = "SELECT o.kaart_nummer, o.geldig_tot, o.klasse, o.saldo, op.product_nummer FROM ov_chipkaart o JOIN ov_chipkaart_product op ON o.kaart_nummer = op.kaart_nummer WHERE o.reiziger_id =?";
            PreparedStatement pst = connection.prepareStatement(findByReiziger);
            pst.setInt(1, reiziger.getId());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                for (Product p : producten) {
                   if (!producten.contains(rs.getInt("op.product_nummer"))) {
                       producten.remove(p);
                   }
                }

                OVChipkaart ovChipkaart = new OVChipkaart(rs.getInt("o.kaart_nummer"), rs.getDate("o.geldig_tot"), rs.getInt("o.klasse"), rs.getInt("o.saldo"), reiziger, producten);
                ovChipkaartArrayList.add(ovChipkaart);
            }
            pst.close();
            rs.close();

        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return ovChipkaartArrayList;
    }

    @Override
    public boolean save(OVChipkaart ovChipkaart) throws SQLException {
        try {
            String save = "INSERT INTO ov_chipkaart(kaart_nummer, geldig_tot, klasse, saldo, reiziger_id)" + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(save);
            pst.setInt(1, ovChipkaart.getKaartnummer());
            pst.setDate(2, ovChipkaart.getGeldingTot());
            pst.setInt(3, ovChipkaart.getKlasse());
            pst.setDouble(4, ovChipkaart.getSaldo());
            pst.setInt(5, ovChipkaart.getReiziger().getId());
            ResultSet r = pst.executeQuery();

            pst.close();
            r.close();

        } catch (SQLException e ){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(OVChipkaart ovChipkaart) throws SQLException {
        try {
            String update = "UPDATE ov_chipkaart " + "SET kaart_nummer =?, geldig_tot =?, klasse =?, saldo =?, reiziger_id =?" + "WHERE reiziger_id =?";
            PreparedStatement pst = connection.prepareStatement(update);
            pst.setInt(1, ovChipkaart.getKaartnummer());
            pst.setDate(2, ovChipkaart.getGeldingTot());
            pst.setInt(3, ovChipkaart.getKlasse());
            pst.setDouble(4, ovChipkaart.getSaldo());
            pst.setInt(5, ovChipkaart.getReiziger().getId());
            pst.setInt(6, ovChipkaart.getReiziger().getId());
            ResultSet r = pst.executeQuery();
            pst.close();
            r.close();

        } catch (SQLException e ){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(OVChipkaart ovChipkaart) throws SQLException {
        try {
            String delete = "DELETE FROM ov_chipkaart WHERE kaart_nummer =?";
            PreparedStatement pst = connection.prepareStatement(delete);
            pst.setInt(1, ovChipkaart.getKaartnummer());
            ResultSet r = pst.executeQuery();
            pst.close();
            r.close();

        } catch (SQLException e ){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<OVChipkaart> findAll() throws SQLException {

        List<OVChipkaart> ovChipkaarten = new ArrayList<>();
        List<Product> producten = productDAO.findAll();
        List<Product> producten1 = new ArrayList<>();

        try {
            String findByReiziger = "SELECT o.kaart_nummer, o.geldig_tot, o.klasse, o.saldo, op.product_nummer FROM ov_chipkaart o JOIN ov_chipkaart_product op ON o.kaart_nummer = op.kaart_nummer";
            String findAll = "SELECT * FROM ovChipkaarten";
            PreparedStatement pst = connection.prepareStatement(findAll);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                for (Product p : producten) {
                    if (producten.contains(rs.getInt("op.product_nummer"))) {
                        producten1.add(p);
                    }
                    OVChipkaart ovChipkaart = new OVChipkaart(rs.getInt("kaart_nummer"), rs.getDate("geldig_tot"), rs.getInt("klasse"), rs.getDouble("saldo"), reizigerDAO.findById(rs.getInt("reiziger_id")), producten);
                    ovChipkaarten.add(ovChipkaart);
                }
            }
            pst.close();
            rs.close();

        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return ovChipkaarten;
    }

}
