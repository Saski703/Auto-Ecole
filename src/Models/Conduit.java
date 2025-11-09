package Models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Conduit extends Seance{
    private Vehicule vehicule;
    Conduit(int num, LocalDate date, LocalTime heure, Moniteur moniteur, int prix, Vehicule vehicule) {
        super(num, date, heure, moniteur, prix);
        this.vehicule = vehicule;
    }
}
