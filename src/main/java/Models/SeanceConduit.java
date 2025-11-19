package Models;

import java.time.LocalDate;
import java.time.LocalTime;

public class SeanceConduit extends Seance{
    private Vehicule vehicule;

    public SeanceConduit() {}
    public SeanceConduit(int num, LocalDate date, LocalTime heure, Moniteur moniteur, double prix, Vehicule vehicule) {
        super(num, date, heure, moniteur, prix);
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
        return "SeanceConduit{" +
                "num=" + num +
                ", date='" + date + '\'' +
                ", heure='" + heure + '\'' +
                ", cinmMoniteur=" + moniteur.getCin() +
                ", prix='" + prix + '\'' +
                ", vehiculeMat=" + vehicule.getMat() +
                '}';
    }

    @Override public String getType() { return "CONDUITE"; }
}
