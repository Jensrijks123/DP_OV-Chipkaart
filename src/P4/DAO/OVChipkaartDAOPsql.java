package P4.DAO;

import P4.Domain.Adres;
import P4.Domain.OVChipkaart;
import P4.Domain.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartDAOPsql implements OVChipkaartDAO{

    private Connection connection;

    public OVChipkaartDAOPsql(Connection connection) throws SQLException {
        this.connection = connection;
    }

    ReizigerDAO reizigerDAO = new ReizigerDAOPsql(connection);

    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) throws SQLException {

        List<OVChipkaart> ovChipkaartArrayList = new ArrayList<>();

        try {
            String findByDatum = "SELECT * FROM ov_chipkaart WHERE reiziger_id =?";
            PreparedStatement pst = connection.prepareStatement(findByDatum);
            pst.setInt(1, reiziger.getId());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                OVChipkaart ovChipkaart = new OVChipkaart(rs.getInt("kaart_nummer"), rs.getDate("geldig_tot"), rs.getInt("klasse"), rs.getInt("saldo"), reiziger);
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
            String delete = "DELETE FROM ov_chipkaart WHERE reiziger_id =?";
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

        try {
            String findAll = "SELECT * FROM ovChipkaarten";
            PreparedStatement pst = connection.prepareStatement(findAll);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                OVChipkaart ovChipkaart = new OVChipkaart(rs.getInt("kaart_nummer"), rs.getDate("geldig_tot"), rs.getInt("klasse"), rs.getDouble("saldo"), reizigerDAO.findById(rs.getInt("reiziger_id")));
                ovChipkaarten.add(ovChipkaart);
            }
            pst.close();
            rs.close();

        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return ovChipkaarten;
    }

}
