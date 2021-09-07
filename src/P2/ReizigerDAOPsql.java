package P2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO{
    private Connection connection;

    public ReizigerDAOPsql(Connection connection) throws SQLException {
        this.connection = connection;
    }

    @Override
    public boolean save(Reiziger reiziger){
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
        } catch (SQLException e ){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Reiziger reiziger) {
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
        } catch (SQLException e ){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        try {
            String delete = "DELETE FROM reiziger WHERE reiziger_id =?";
            PreparedStatement pst = connection.prepareStatement(delete);
            pst.setInt(1, reiziger.getId());
            ResultSet r = pst.executeQuery();
            pst.close();
        } catch (SQLException e ){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Reiziger findById(int id) {

        String s = "";

        try {
            String findById = "SELECT * FROM reiziger WHERE reiziger_id =?";
            PreparedStatement pst = connection.prepareStatement(findById);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                s = s + rs.getInt("reiziger_id") + " " + rs.getString("voorletters") + ". " + rs.getString("achternaam");
            }
            System.out.println(s);
            pst.close();

        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return null;
//        return s;
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum) {

//        List<Reiziger> s = null;
        List<String> s = null;

        try {
            String findByDatum = "SELECT * FROM reiziger WHERE geboortedatum =?";
            PreparedStatement pst = connection.prepareStatement(findByDatum);
            pst.setString(1, datum);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                s.add(rs.getInt("reiziger_id") + " " + rs.getString("voorletters") + ". " + rs.getString("achternaam"));
            }
            System.out.println(s);
            pst.close();

        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return null;
//        return s;
    }


    @Override
    public List<Reiziger> findAll() throws SQLException {

//        List<Reiziger> s = null;
        List<String> ss = null;

        try {
            String findAll = "SELECT * FROM reiziger";
            PreparedStatement pst = connection.prepareStatement(findAll);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                ss.add(rs.getInt("reiziger_id") + " " + rs.getString("voorletters") + ". " + rs.getString("achternaam"));
            }
            System.out.println(ss);
            pst.close();

        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return null;
//        return ss;
    }
}
