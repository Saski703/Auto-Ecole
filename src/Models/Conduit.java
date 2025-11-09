package Models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Conduit extends Seance{

    Conduit(int num, LocalDate date, LocalTime heure, Moniteur moniteur, int prix) {
        super(num, date, heure, moniteur, prix);
    }
}
