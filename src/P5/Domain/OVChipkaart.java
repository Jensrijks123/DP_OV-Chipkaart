package P5.Domain;

import java.sql.Date;
import java.util.List;

public class OVChipkaart {

    private int kaartnummer;
    private Date geldingTot;
    private int klasse;
    private double saldo;
    private Reiziger reiziger;
    private List<Product> producten;

    public OVChipkaart(int kaartnummer, Date geldingTot, int klasse, double saldo, Reiziger reiziger, List<Product> producten) {
        this.kaartnummer = kaartnummer;
        this.geldingTot = geldingTot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger = reiziger;
        this.producten = producten;
    }

    public int getKaartnummer() {
        return kaartnummer;
    }

    public void setKaartnummer(int kaartnummer) {
        this.kaartnummer = kaartnummer;
    }

    public Date getGeldingTot() {
        return geldingTot;
    }

    public void setGeldingTot(Date geldingTot) {
        this.geldingTot = geldingTot;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public List<Product> getProducten() {
        return producten;
    }

    public void setProducten(List<Product> producten) {
        this.producten = producten;
    }

    public void addProducten(List<Product> producten, Product product) {
        producten.add(product);
    }

    public void deleteProducten(List<Product> producten, Product product) {
        producten.remove(product);
    }

    @Override
    public String toString() {
        return "OVChipkaart{" +
                "kaartnummer=" + kaartnummer +
                ", geldingTot=" + geldingTot +
                ", klasse=" + klasse +
                ", saldo=" + saldo +
                '}';
    }
}
