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
        return false;
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        return false;
    }

    @Override
    public Reiziger findById(int id) {
        return null;
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum) {
        return null;
    }

    @Override
    public List<Reiziger> findAll() throws SQLException {
        List<Reiziger> reizigers = null;
        String s ="";

        Statement st = connection.createStatement();

        ResultSet rs = st.executeQuery("SELECT * FROM reiziger");


        System.out.println("Alle reizigers:");

        while (rs.next())
        {
            String tussen = rs.getString("tussenvoegsel");
            if (tussen == null){
                tussen = "";
            }
            System.out.println("    #" + rs.getInt("reiziger_id") + ": " + rs.getString("voorletters")+". "+ tussen + " " +  rs.getString("achternaam")+" ("+rs.getDate("geboortedatum")+")");
        }
        return reizigers;
    }
}
