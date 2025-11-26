package Models;

import java.time.LocalDate;
import java.time.LocalTime;

public class SeanceConduit extends Seance{
    private Vehicule vehicule;

    public SeanceConduit() {}
    public SeanceConduit(int num, LocalDate date, LocalTime heure, Moniteur moniteur, Candidat candidat, double prix, Vehicule vehicule) {
        super(num, date, heure, moniteur, candidat, prix);
        this.vehicule = vehicule;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    @Override
    public String toString() {
        return """
           SeanceConduit details:
           ----------------------
           ID        : %d
           Date      : %s at %s
           Prix      : %s
           Moniteur  : %s
           Candidat  : %s
           Vehicule  : %s
           """.formatted(
                num,
                date,
                heure,
                prix,
                (moniteur != null ? moniteur.getCin() : "N/A"),
                (candidat != null ? candidat.getNumCin() : "N/A"),
                (vehicule != null ? vehicule.getMat() : "N/A")
        );
    }

    @Override public String getType() { return "CONDUITE"; }
}
