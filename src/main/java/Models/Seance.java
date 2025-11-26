package Models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.LocalDate;
import java.time.LocalTime;
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
// 2. Map the values of "type" to the specific classes
@JsonSubTypes({
        @JsonSubTypes.Type(value = SeanceCode.class, name = "CODE"),
        @JsonSubTypes.Type(value = SeanceConduit.class, name = "CONDUITE")
})
public abstract class Seance {
    protected int num;
    protected LocalDate date;
    protected LocalTime heure;
    protected Moniteur moniteur;
    protected Candidat candidat;
    protected double prix;

    // --------------------Constructeurs--------------------
    public Seance() {}
    public Seance(int num, LocalDate date, LocalTime heure, Moniteur moniteur, Candidat candidat,double prix) {
        this.num = num;
        this.date = date;
        this.heure = heure;
        this.moniteur = moniteur;
        this.candidat = candidat;
        this.prix = prix;
    }

    //
    public abstract String getType();
    // -------------------- Getters and Setters --------------------
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHeure() {
        return heure;
    }

    public void setHeure(LocalTime heure) {
        this.heure = heure;
    }

    public Moniteur getMoniteur() {
        return moniteur;
    }

    public void setMoniteur(Moniteur moniteur) {
        this.moniteur = moniteur;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    // -------------------- toString --------------------
    @Override
    public String toString() {
        return "Seance{" +
                "num=" + num +
                ", date='" + date + '\'' +
                ", heure='" + heure + '\'' +
                ", cinmMoniteur=" + moniteur.getCin() +
                ", cinmCandidat=" + candidat.getNumCin() +
                ", prix='" + prix + '\'' +
                '}';
    }
}
