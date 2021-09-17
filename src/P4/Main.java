package P4;


import P4.DAO.*;
import P4.Domain.Adres;
import P4.Domain.OVChipkaart;
import P4.Domain.Reiziger;

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

        OVChipkaartDAO ovChipkaartDAO = new OVChipkaartDAOPsql(connection);

        testOVChipkaartDAOP(ovChipkaartDAO);

    }

    private static void testOVChipkaartDAOP(OVChipkaartDAO odao) throws SQLException {

        System.out.println("\n---------- Test OVChipkaartDAO -------------");

        // save Ovchipkaart
        System.out.print("[Test]  ");
        Reiziger gozer1 = new Reiziger(19, "B", "", "Bernardo", java.sql.Date.valueOf("2002-09-19"), null, null);
        OVChipkaart ovChipkaart1 = new OVChipkaart(212011, java.sql.Date.valueOf("2021-12-03"), 2, 2.5, gozer1);
        odao.save(ovChipkaart1);
        System.out.println();


        // Find by Reiziger
        String gbdatumReiziger = "1991-12-03";
        Reiziger reizigerTesting = new Reiziger(5, "F", null, "Memari", java.sql.Date.valueOf(gbdatumReiziger), null, null);
        System.out.println("[Test]");
        System.out.println(odao.findByReiziger(reizigerTesting));
        System.out.println();

    }
}

