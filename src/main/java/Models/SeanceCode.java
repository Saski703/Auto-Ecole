package Models;

import java.time.LocalDate;
import java.time.LocalTime;

public class SeanceCode extends Seance{

    public SeanceCode() {}
    public SeanceCode(int num, LocalDate date, LocalTime heure, Moniteur moniteur, double prix) {
        super(num, date, heure, moniteur, prix);
    }

    @Override
    public String toString() {
        return "SeanceCode{" +
                "num=" + num +
                ", date='" + date + '\'' +
                ", heure='" + heure + '\'' +
                ", cinmMoniteur=" + moniteur.getCin() +
                ", prix='" + prix + '\'' +
                '}';
    }

    @Override public String getType() { return "CODE"; }
}
