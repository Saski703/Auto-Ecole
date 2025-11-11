package Models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Code extends Seance{

    Code (int num, LocalDate date, LocalTime heure, Moniteur moniteur, double prix) {
        super(num, date, heure, moniteur, prix);
    }
}
