package P5.DAO;

import P5.Domain.Adres;
import P5.Domain.Reiziger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdresDAOPsql implements AdresDAO {

    private Connection connection;

    public AdresDAOPsql(Connection connection) throws SQLException {
        this.connection = connection;
    }

    ReizigerDAO reizigerDAO = new ReizigerDAOPsql(connection);

    @Override
    public boolean save(Adres adres) throws SQLException {
        try {
            String save = "INSERT INTO adres(adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id)" + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(save);
            pst.setInt(1, adres.getId());
            pst.setString(2, adres.getPostcode());
            pst.setString(3, adres.getHuisnummer());
            pst.setString(4, adres.getStraat());
            pst.setString(5, adres.getWoonplaats());
            pst.setInt(6, adres.getReiziger().getId());
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
    public boolean update(Adres adres) throws SQLException{
        try {
            String update = "UPDATE adres " + "SET adres_id =?, postcode =?, huisnummer =?, straat =?, woonplaats =?, reiziger_id =?" + "WHERE adres_id =?";
            PreparedStatement pst = connection.prepareStatement(update);
            pst.setInt(1, adres.getId());
            pst.setString(2, adres.getPostcode());
            pst.setString(3, adres.getHuisnummer());
            pst.setString(4, adres.getStraat());
            pst.setString(5, adres.getWoonplaats());
            pst.setInt(6, adres.getReiziger().getId());
            pst.setInt(7, adres.getId());
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
    public boolean delete(Adres adres) throws SQLException{
        try {
            String delete = "DELETE FROM adres WHERE adres_id =?";
            PreparedStatement pst = connection.prepareStatement(delete);
            pst.setInt(1, adres.getId());
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
    public Adres findByReiziger(Reiziger reiziger) throws SQLException{

        try {
            String findByDatum = "SELECT * FROM adres WHERE reiziger_id =?";
            PreparedStatement pst = connection.prepareStatement(findByDatum);
            pst.setInt(1, reiziger.getId());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Adres adres = new Adres(rs.getInt("adres_id"), rs.getString("postcode"), rs.getString(3), rs.getString("straat"), rs.getString(5), reizigerDAO.findById(rs.getInt("reiziger_id")));
                return adres;
            }
            pst.close();
            rs.close();

        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Adres> findAll() throws SQLException{

        List<Adres> adressen = new ArrayList<>();

        try {
            String findAll = "SELECT * FROM adres";
            PreparedStatement pst = connection.prepareStatement(findAll);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Adres adres = new Adres(rs.getInt("adres_id"), rs.getString("postcode"), rs.getString(3), rs.getString("straat"), rs.getString(5), reizigerDAO.findById(rs.getInt("reiziger_id")));
                adressen.add(adres);
            }
            pst.close();
            rs.close();

        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return adressen;
    }
}
