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
}
