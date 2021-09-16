package P4.DAO;


import P4.Domain.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO {
    private Connection connection;

    AdresDAO adresDAO = new AdresDAOPsql(connection);

    public ReizigerDAOPsql(Connection connection) throws SQLException {
        this.connection = connection;
    }

    @Override
    public boolean save(Reiziger reiziger) throws SQLException{
        try {
            String save = "INSERT INTO reiziger(reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum)" + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(save);
            pst.setInt(1, reiziger.getId());
            pst.setString(2, reiziger.getVoorletters());
            pst.setString(3, reiziger.getTussenvoegsel());
            pst.setString(4, reiziger.getAchternaam());
            pst.setDate(5, reiziger.getGeboortedatum());
            ResultSet r = pst.executeQuery();
            pst.close();
            r.close();

            if (reiziger.getAdres() != null) {
                adresDAO.save(reiziger.getAdres());
            }

        } catch (SQLException e ){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Reiziger reiziger) throws SQLException {
        try {
            String update = "UPDATE reiziger " + "SET reiziger_id =?, voorletters =?, tussenvoegsel =?, achternaam =?, geboortedatum =?" + "WHERE reiziger_id =?";
            PreparedStatement pst = connection.prepareStatement(update);
            pst.setInt(1, reiziger.getId());
            pst.setString(2, reiziger.getVoorletters());
            pst.setString(3, reiziger.getTussenvoegsel());
            pst.setString(4, reiziger.getAchternaam());
            pst.setDate(5, reiziger.getGeboortedatum());
            pst.setInt(6, reiziger.getId());
            ResultSet r = pst.executeQuery();
            pst.close();
            r.close();

            if (reiziger.getAdres() != null) {
                adresDAO.update(reiziger.getAdres());
            }

        } catch (SQLException e ){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Reiziger reiziger) throws SQLException {
        try {
            String delete = "DELETE FROM reiziger WHERE reiziger_id =?";
            PreparedStatement pst = connection.prepareStatement(delete);
            pst.setInt(1, reiziger.getId());
            ResultSet r = pst.executeQuery();
            pst.close();
            r.close();

            if (reiziger.getAdres() != null) {
                adresDAO.delete(reiziger.getAdres());
            }

        } catch (SQLException e ){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Reiziger findById(int id) throws SQLException {

        try {
            String findById = "SELECT * FROM reiziger WHERE reiziger_id =?";
            PreparedStatement pst = connection.prepareStatement(findById);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Reiziger reiziger = new Reiziger(rs.getInt("reiziger_id"), rs.getString("voorletters"), rs.getString(3), rs.getString("achternaam"), rs.getDate(5), adresDAO.findByReiziger(findById(id)));
                return reiziger;
            }
            pst.close();
            rs.close();

        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum) throws SQLException {

        List<Reiziger> reizigers = new ArrayList<>();

        try {
            String findByDatum = "SELECT * FROM reiziger WHERE geboortedatum =?";
            PreparedStatement pst = connection.prepareStatement(findByDatum);
            pst.setDate(1, Date.valueOf(datum));
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Reiziger reiziger = new Reiziger(rs.getInt("reiziger_id"), rs.getString("voorletters"), rs.getString(3), rs.getString("achternaam"), rs.getDate(5), adresDAO.findByReiziger(findById(rs.getInt("reiziger_id"))));
                reizigers.add(reiziger);
            }
            pst.close();
            rs.close();

        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return reizigers;
    }


    @Override
    public List<Reiziger> findAll() throws SQLException {

        List<Reiziger> reizigers = new ArrayList<>();

        try {
            String findAll = "SELECT * FROM reiziger";
            PreparedStatement pst = connection.prepareStatement(findAll);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Reiziger reiziger = new Reiziger(rs.getInt("reiziger_id"), rs.getString("voorletters"), rs.getString(3), rs.getString("achternaam"), rs.getDate(5), adresDAO.findByReiziger(findById(rs.getInt("reiziger_id"))));
                reizigers.add(reiziger);
            }
            pst.close();
            rs.close();

        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return reizigers;
    }
}
