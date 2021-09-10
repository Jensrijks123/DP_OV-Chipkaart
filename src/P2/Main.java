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

        testReizigerDAO(reizigerDAO);

    }

//    public static Connection getConnection() throws SQLException {
//
//    }

//    private static void closeConnection() {
//
//    }

    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Save Reiziger
        System.out.print("[Test]  ");
        Reiziger jens = new Reiziger(24, "B", "", "Bernardo", java.sql.Date.valueOf("2002-09-19"));
        rdao.save(jens);
        System.out.println();

        // Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();


        // Maak een nieuwe reiziger aan en persisteer deze in de database
        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger(89, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");


        // Find by Date
        String gbdatumTest = "2002-09-17";
        List<Reiziger> reizigersDatum = rdao.findByGbdatum(gbdatumTest);
        System.out.println("[Test]");
        for (Reiziger ra : reizigersDatum) {
            System.out.println(ra);
        }

        System.out.println();


        // Find by ID
        int idTest = 19;
        System.out.println("[Test]");
        System.out.println(rdao.findById(idTest));

        System.out.println();


        // Delete
        String gbdatumDelete = "2002-12-03";
        Reiziger reizigerDelete = new Reiziger(77, "F", null, "Memari", java.sql.Date.valueOf(gbdatumDelete));
        System.out.println("[Test]");
        System.out.println(rdao.delete(reizigerDelete));

        System.out.println();


        // Update
        String gbdatumUpdate = "2012-11-07";
        Reiziger reizigerUpdate = new Reiziger(77, "D", null, "Bonk", java.sql.Date.valueOf(gbdatumUpdate));
        System.out.println("[Test]");
        System.out.println(rdao.update(reizigerUpdate));

    }
}
