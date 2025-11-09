package Models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Seance {
    protected int num;
    protected LocalDate date;
    protected LocalTime heure;
    protected Moniteur moniteur;
    protected double prix;

    Seance(int num, LocalDate date, LocalTime heure, Moniteur moniteur, double prix) {
        this.num = num;
        this.date = date;
        this.heure = heure;
        this.moniteur = moniteur;
        this.prix = prix;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHeure() {
        return heure;
    }

    public void setHeure(LocalTime heure) {
        this.heure = heure;
    }

    public Moniteur getMoniteur() {
        return moniteur;
    }

    public void setMoniteur(Moniteur moniteur) {
        this.moniteur = moniteur;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
