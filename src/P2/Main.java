package P2;

import java.sql.*;
import java.util.List;
import java.util.Properties;

public class Main {

    public static Connection connection;

    public static void main(String[] args) throws SQLException {

        System.out.println("Test");


        String gebruikersnaam = "postgres";
        String Wachtwoord = "Password";

        String url = "jdbc:postgresql://localhost:5432/ovchip";

        Properties p = new Properties();
        p.setProperty("user",gebruikersnaam);
        p.setProperty("password",Wachtwoord);

        connection = DriverManager.getConnection(url,p);

        ReizigerDAO reizigerDAO = new ReizigerDAOPsql(connection);
        Reiziger jens = new Reiziger(19, "J", "", "Rijks", java.sql.Date.valueOf("2002-04-19"));
        reizigerDAO.save(jens);
        System.out.println(jens.getGeboortedatum());

    }

//    public static Connection getConnection() throws SQLException {
//
//    }

    private static void closeConnection() {

    }

    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        // Maak een nieuwe reiziger aan en persisteer deze in de database
        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        // Voeg aanvullende tests van de ontbrekende CRUD-operaties in.
    }
}
