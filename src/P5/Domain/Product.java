package P5.Domain;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private int id;
    private String naam;
    private String beschrijving;
    private Double prijs;
    private List<Integer> ovChipkaartenNummers = new ArrayList<>();

    public Product(int id, String naam, String beschrijving, Double prijs) {
        this.id = id;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public Double getPrijs() {
        return prijs;
    }

    public void setPrijs(Double prijs) {
        this.prijs = prijs;
    }

    public List<Integer> getOvChipkaartenNummers() {
        return ovChipkaartenNummers;
    }

    public void setOvChipkaartenNummers(List<Integer> ovChipkaartenNummers) {
        this.ovChipkaartenNummers = ovChipkaartenNummers;
    }

    public boolean addOvChipkaartNummer(Integer ovChipkaart) {

        Boolean toegevoegd = false;

        for (Integer i : ovChipkaartenNummers) {
            if (i.equals(ovChipkaart)) {
                ovChipkaartenNummers.add(i);
                toegevoegd = true;
            }
        }
        return toegevoegd;
    }


    public boolean deleteOvChipkaartNummer(Integer ovChipkaart) {

        Boolean verwijderd = false;

        for (Integer i : ovChipkaartenNummers) {
            if (i.equals(ovChipkaart)) {
                ovChipkaartenNummers.remove(i);
                verwijderd = true;
            }
        }
        return verwijderd;
    }

    @Override
    public String toString() {

        return "Product{" +
                "id=" + id +
                ", naam='" + naam + '\'' +
                ", beschrijving='" + beschrijving + '\'' +
                ", prijs=" + prijs +
                ", ovChipkaarten=" + ovChipkaartenNummers+
                '}';
    }
}
