package Models;

import java.time.LocalDate;
import java.time.LocalTime;

public class ExamenConduit extends Examen {

    private Vehicule vehicule;

    public ExamenConduit() { super(); }

    public ExamenConduit(int num, LocalDate date, LocalTime heure, Moniteur moniteur, Candidat candidat, double prix, int result,Vehicule vehicule) {
        super(num, date, heure, moniteur, candidat, prix, result);
        this.vehicule = vehicule;
    }

    public Vehicule getVehicule() { return vehicule; }
    public void setVehicule(Vehicule vehicule) { this.vehicule = vehicule; }

    @Override
    public String getType() {
        return "EXAMEN_CONDUITE";
    }

    @Override
    public String toString() {
        String vInfo = (vehicule != null) ? vehicule.getMat() : "Aucun";
        return "🎓 [EXAMEN CONDUITE] " + super.toString() + " | 🚘 Véhicule: " + vInfo;
    }
}