package Models;

import java.time.LocalDate;

public class Vehicule {
    private int numMat;
    private String type;
    private LocalDate date;
    private double kmTotale;
    private LocalDate dateVignette;
    private LocalDate dateAssurance;
    private LocalDate dateVisiteTechnique;
    private LocalDate dateVidange;

    public Vehicule() {}
    public Vehicule(int numMat,String type, LocalDate date, double kmTotale,LocalDate dateVignette,LocalDate dateAssurance,LocalDate dateVisiteTechnique,LocalDate dateVidange) {
        this.type = type;
        this.numMat = numMat;
        this.date = date;
        this.kmTotale = kmTotale;
        this.dateVignette=dateVignette;
        this.dateAssurance=dateAssurance;
        this.dateVisiteTechnique=dateVisiteTechnique;
        this.dateVidange=dateVidange;
    }

    public LocalDate getDateVignette() {
        return dateVignette;
    }

    public void setDateVignette(LocalDate dateVignette) {
        this.dateVignette = dateVignette;
    }

    public LocalDate getDateAssurance() {
        return dateAssurance;
    }

    public void setDateAssurance(LocalDate dateAssurance) {
        this.dateAssurance = dateAssurance;
    }

    public LocalDate getDateVisiteTechnique() {
        return dateVisiteTechnique;
    }

    public void setDateVisiteTechnique(LocalDate dateVisiteTechnique) {
        this.dateVisiteTechnique = dateVisiteTechnique;
    }

    public LocalDate getDateVidange() {
        return dateVidange;
    }

    public void setDateVidange(LocalDate dateVidange) {
        this.dateVidange = dateVidange;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumMat() {
        return numMat;
    }

    public void setNumMat(int numMat) {
        this.numMat = numMat;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getKmTotale() {
        return kmTotale;
    }

    public void setKmTotale(double kmTotale) {
        this.kmTotale = kmTotale;
    }


    @Override
    public String toString() {
        return "Vehicule{" +
                "type='" + type + '\'' +
                ", numMat=" + numMat +
                ", date=" + date +
                ", kmTotale=" + kmTotale +
                ", dateVignette=" + dateVignette +
                ", dateAssurance=" + dateAssurance +
                ", dateVisiteTechnique=" + dateVisiteTechnique +
                ", dateVidange=" + dateVidange +
                '}';
    }
    private long joursRestants(LocalDate date) {
        if (date == null) return -1;
        return java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), date);
    }

    private int kmRestantVidange() {
        int seuilVidange = 5000; // tous les 5000 km
        return seuilVidange - (int)(kmTotale % seuilVidange);
    }

    // ----- Nouvelle fonction pour afficher les alertes -----
    public void afficherAlertes() {
        long assuranceRest = joursRestants(dateAssurance);
        if (assuranceRest >= 0) {
            System.out.println("Assurance | Jours restants : " + assuranceRest + (assuranceRest <= 50 ? " ⚠️ Bientôt expirée!" : ""));
        }

        long vignetteRest = joursRestants(dateVignette);
        if (vignetteRest >= 0) {
            System.out.println("Vignette | Jours restants : " + vignetteRest + (vignetteRest <= 50 ? " ⚠️ Bientôt expirée!" : ""));
        }

        long visiteRest = joursRestants(dateVisiteTechnique);
        if (visiteRest < 365 && visiteRest >=0 && date.getYear() > 10) {
            System.out.println("Visite Technique | Jours restants : " + visiteRest + (visiteRest <= 50 ? " ⚠️ Bientôt expirée!" : ""));

        }else{
            System.out.println("vehicule jdaida new new !!");
        }

        int kmRest = kmRestantVidange();
        System.out.println("Vidange | Km restant avant prochaine : " + kmRest + " km" + (kmRest <= 100 ? " ⚠️ Bientôt à faire!" : ""));
    }
}