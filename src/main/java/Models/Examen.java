package Models;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Examen extends Seance {

    protected int result;

    public Examen() { super(); }

    public Examen(int num, LocalDate date, LocalTime heure, Moniteur moniteur, Candidat candidat, double prix,  int result) {
        super(num, date, heure, moniteur, candidat, prix);
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}