package P3;

import P3.DAO.AdresDAO;
import P3.DAO.AdresDAOPsql;
import P3.DAO.ReizigerDAO;
import P3.DAO.ReizigerDAOPsql;
import P3.Domain.Adres;
import P3.Domain.Reiziger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
        p.setProperty("user", gebruikersnaam);
        p.setProperty("password", Wachtwoord);

        connection = DriverManager.getConnection(url, p);

        AdresDAO AdresDAO = new AdresDAOPsql(connection);

        testAdresDAO(AdresDAO);

    }

    private static void testAdresDAO(AdresDAO adao) throws SQLException {

        System.out.println("\n---------- Test AdresDAO -------------");

        // Save Adres
        System.out.print("[Test]  ");
        Reiziger gozer = new Reiziger(19, "B", "", "Bernardo", java.sql.Date.valueOf("2002-09-19"), null);
        Adres benschop = new Adres(24, "3401BI", "101", "BernardoWijk", "Benschop", gozer);
        adao.save(benschop);
        System.out.println();


        // Update Adres
        String gbdatumUpdate = "2012-11-07";
        Reiziger reizigerUpdate = new Reiziger(19, "D", null, "Bonk", java.sql.Date.valueOf(gbdatumUpdate), null);
        Adres utrecht = new Adres(24, "1461BJ", "191", "Whilhelm", "Utrecht", reizigerUpdate);
        System.out.println("[Test]");
        System.out.println(adao.update(utrecht));
        System.out.println();


        // Delete
        String gbdatumDelete = "2002-12-03";
        Reiziger reizigerDelete = new Reiziger(19, "F", null, "Memari", java.sql.Date.valueOf(gbdatumDelete), null);
        Adres utrecht2 = new Adres(24, "1461BJ", "191", "Whilhelm", "Utrecht", reizigerDelete);
        System.out.println("[Test]");
        System.out.println(adao.delete(utrecht2));

        System.out.println();


        // Haal alle adressen op uit de database
        List<Adres> adressen = adao.findAll();
        System.out.println("[Test] findAll() geeft de volgende adressen:");
        for (Adres a : adressen) {
            System.out.println(a);
        }
        System.out.println();


        // Find by Reiziger
        String gbdatumReiziger = "1991-12-03";
        Reiziger reizigerTesting = new Reiziger(5, "F", null, "Memari", java.sql.Date.valueOf(gbdatumReiziger), null);
        List<Adres> adressenReizger = adao.findByReiziger(reizigerTesting);
        System.out.println("[Test]");
        for (Adres ar : adressenReizger) {
            System.out.println(ar);
        }

        System.out.println();

    }
}

