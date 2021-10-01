package P5;


import P5.DAO.*;
import P5.Domain.OVChipkaart;
import P5.Domain.Product;
import P5.Domain.Reiziger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

        try {
            ProductDAO productDAO1 = new ProductDAOPsql(connection);
            OVChipkaartDAO ovChipkaartDAO1 = new OVChipkaartDAOPsql(connection);

            testProductDAOP(productDAO1);
            testOVChipkaartDAOP(ovChipkaartDAO1);
        } catch (StackOverflowError e){
            System.out.printf("asdasdasd");
            System.out.printf(e.getMessage());
        }
    }

    private static void testProductDAOP(ProductDAO pdao) throws SQLException {
        OVChipkaartDAO ovChipkaartDAO = new OVChipkaartDAOPsql(connection);

        System.out.println("\n---------- Test ProductDAOP -------------");

        // save Product
        System.out.print("[Test]  ");
        Reiziger gozer1 = new Reiziger(19, "B", "", "Bernardo", java.sql.Date.valueOf("2002-09-19"), null, null);
        Product product = new Product(7, "De Beste", "Super Goedkoop en beter", 12.0, ovChipkaartDAO.findByReiziger(gozer1));
        pdao.save(product);
        System.out.println();


        // find By OVChipkaart
        OVChipkaart ovChipkaart1 = new OVChipkaart(57401, java.sql.Date.valueOf("2021-12-03"), 2, 2.5, gozer1, null);
        System.out.println("[Test]");
        System.out.println(pdao.findByOVChipkaart(ovChipkaart1));
        System.out.println();

    }
    private static void testOVChipkaartDAOP(OVChipkaartDAO odao) throws SQLException{

        System.out.println("\n---------- Test OVChipkaartDAOP -------------");

        // save Ovchipkaart
        System.out.print("[Test]  ");
        Reiziger gozer1 = new Reiziger(19, "B", "", "Bernardo", java.sql.Date.valueOf("2002-09-19"), null, null);
        OVChipkaart ovChipkaart1 = new OVChipkaart(212711, java.sql.Date.valueOf("2021-12-03"), 2, 2.5, gozer1, null);
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

