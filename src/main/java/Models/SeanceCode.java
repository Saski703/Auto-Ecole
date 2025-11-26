package Models;

import java.time.LocalDate;
import java.time.LocalTime;

public class SeanceCode extends Seance{

    public SeanceCode() {}
    public SeanceCode(int num, LocalDate date, LocalTime heure, Moniteur moniteur, Candidat candidat, double prix) {
        super(num, date, heure, moniteur, candidat, prix);
    }

    @Override
    public String toString() {
        return """
           SeanceCode details:
           ----------------------
           ID        : %d
           Date      : %s at %s
           Prix      : %s
           Moniteur  : %s
           Candidat  : %s
           """.formatted(
                num,
                date,
                heure,
                prix,
                (moniteur != null ? moniteur.getCin() : "N/A"),
                (candidat != null ? candidat.getNumCin() : "N/A")
        );
    }

    @Override public String getType() { return "CODE"; }
}
