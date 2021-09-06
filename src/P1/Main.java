package P1;

import java.sql.*;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws SQLException {

        String gebruikersnaam = "postgres";
        String Wachtwoord = "password";

        String url = "jdbc:postgresql://localhost:5432/ovchip";

        Properties p = new Properties();
        p.setProperty("user",gebruikersnaam);
        p.setProperty("password",Wachtwoord);

        Connection con = DriverManager.getConnection(url,p);
        Statement st = con.createStatement();
        try {
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

        rs.close();
        st.close();
        con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
