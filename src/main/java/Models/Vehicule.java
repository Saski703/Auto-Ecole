package Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Vehicule {
    private String Mat, type;
    private LocalDate date, dateVignette, dateAssurance, dateVisiteTechnique, dateVidange;
    private int age;
    private double kmTotale;
    private List<Maintenance> maintenance = new ArrayList<>();

    public Vehicule() {}

    public Vehicule(String Mat, String type, LocalDate date, int age, double kmTotale,
                    LocalDate dateVignette, LocalDate dateAssurance,
                    LocalDate dateVisiteTechnique, LocalDate dateVidange) {
        this.type = type; this.Mat = Mat; this.date = date; this.age = age;
        this.kmTotale = kmTotale; this.dateVignette = dateVignette;
        this.dateAssurance = dateAssurance; this.dateVisiteTechnique = dateVisiteTechnique;
        this.dateVidange = dateVidange;
    }

    public String getMat() { return Mat; }
    public void setMat(String mat) { Mat = mat; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public double getKmTotale() { return kmTotale; }
    public void setKmTotale(double kmTotale) { this.kmTotale = kmTotale; }

    public LocalDate getDateVignette() { return dateVignette; }
    public void setDateVignette(LocalDate dateVignette) { this.dateVignette = dateVignette; }

    public LocalDate getDateAssurance() { return dateAssurance; }
    public void setDateAssurance(LocalDate dateAssurance) { this.dateAssurance = dateAssurance; }

    public LocalDate getDateVisiteTechnique() { return dateVisiteTechnique; }
    public void setDateVisiteTechnique(LocalDate dateVisiteTechnique) { this.dateVisiteTechnique = dateVisiteTechnique; }

    public LocalDate getDateVidange() { return dateVidange; }
    public void setDateVidange(LocalDate dateVidange) { this.dateVidange = dateVidange; }

    public List<Maintenance> getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(List<Maintenance> maintenance) {
        this.maintenance = maintenance;
    }

    @Override
    public String toString() {
        return "Vehicule{" +
                "type='" + type + '\'' +
                ", Mat=" + Mat +
                ", date=" + date +
                ", kmTotale=" + kmTotale +
                ", dateVignette=" + dateVignette +
                ", dateAssurance=" + dateAssurance +
                ", dateVisiteTechnique=" + dateVisiteTechnique +
                ", dateVidange=" + dateVidange +
                '}';
    }
}