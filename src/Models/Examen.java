package Models;

import java.time.LocalDate;

public class Examen {
    private String type;
    private LocalDate date;
    private double prix;
    private double tva;

    public Examen(String type, LocalDate date, double prix, double tva) {
        this.type = type;
        this.date = date;
        this.prix = prix;
        this.tva = tva;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getTva() {
        return tva;
    }

    public void setTva(double tva) {
        this.tva = tva;
    }
    public double prixTotale(){
        return (this.prix+(this.prix*this.tva));
    }
}
