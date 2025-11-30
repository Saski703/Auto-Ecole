package Services;

import Models.Candidat;
import Models.Vehicule;
import Repositories.VehiculeRepositories;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import static java.time.temporal.ChronoUnit.DAYS;

public class VehiculeServices {
    VehiculeRepositories vehiculeRepos = new VehiculeRepositories();
    public void ajoutVehicule(Vehicule v) {
        vehiculeRepos.save(v);
    }
    public Vehicule rechercherVehicule(String Mat) {
        return vehiculeRepos.rechercherVehicule(Mat);
    }

    public boolean suppressionVehicule(String Mat) {
        return vehiculeRepos.suppressionVehicule(Mat);
    }

    private long joursRestants(LocalDate date) {
        if (date == null) return -1;
        return DAYS.between(LocalDate.now(), date);
    }

    public List<String> getAlertes() {
        List<Vehicule> Vehicules = vehiculeRepos.getAllVehicules();
        List<String> alertes = new ArrayList<>();

        for (Vehicule v : Vehicules) {
            System.out.println("========--" + v.getMat() + " --========");
            // 1. Check Assurance
            checkAssurance(v, alertes);

            // 2. Check Vignette
            checkVignette(v, alertes);

            // 3. Check Visite Technique (COMPLEX REGULATION LOGIC)
            checkVisiteTechnique(v, alertes);

            // 4. Check Vidange (Maintenance)
            checkMaintenance(v, alertes);
        }

        return alertes;
    }

    public void checkAssurance(Vehicule v, List<String>alertes){
        Long joursRestants = joursRestants(v.getDateAssurance());
        if (joursRestants <= 0) {
            alertes.add("[ASSURANCE] " + v.getMat() + " : Expirée depuis " + Math.abs(joursRestants) + " jours");
        } else if (joursRestants <= 30) {
            alertes.add("[ASSURANCE] " + v.getMat() + " : Expirée dans " + Math.abs(joursRestants) + " jours");
        }
    }
    public void checkVignette(Vehicule v, List<String>alertes){
        Long joursRestants = joursRestants(v.getDateVignette());
        if (joursRestants <= 0) {
            alertes.add("[VIGENTTE] " + v.getMat() + " : Expirée depuis " + Math.abs(joursRestants) + " jours");
        } else if (joursRestants <= 30) {
            alertes.add("[VIGENTTE] " + v.getMat() + " : Expirée dans " + Math.abs(joursRestants) + " jours");
        }
    }
    public void checkVisiteTechnique(Vehicule v, List<String>alertes){
        int age = v.getAge();
        String type = v.getType().toLowerCase();

        // Change "jourRestants"?
        long joursRestants = joursRestants(v.getDateVisiteTechnique());

        String frequence = "Inconnue";

        if (type.equals("voiture")) {
            if (age < 4) alertes.add("Première visite à 4 ans");
            else if (joursRestants > (365*2)){
                alertes.add("Tous les 2 ans");
            }
        }
        else {
            if (age < 2) alertes.add("Première visite à 2 ans");
            else if (joursRestants > 365){
                frequence = "Annuelle";
            }
        }

        if (joursRestants < 0) {
            alertes.add("[VISITE TECHNIQUE] " + v.getMat() + " : EXPIRÉE (" + frequence + ")");
        } else if (joursRestants <= 30) {
            alertes.add("[VISITE TECHNIQUE] " + v.getMat() + " : Dans " + joursRestants + " jours (" + frequence + ")");
        }
    }
    public void checkMaintenance(Vehicule v, List<String>alertes){
        long intervalleEnKm = 15000;
        long kmRestants = intervalleEnKm - (int)(v.getKmTotale() % intervalleEnKm) ;
        if (kmRestants <= 500) {
            alertes.add("🔧 [MAINTENANCE] " + v.getMat() + " : Limite KM atteinte (" + kmRestants + " km restants)");
        }else if (v.getDateVidange().getYear() < LocalDate.now().getYear()) {
            alertes.add("🔧 [MAINTENANCE] " + v.getMat() + " :  (> 1 an). Vidange nécessaire.");
        }
    }

    public void afficherVehicules(){
        List<Vehicule> vehicules = vehiculeRepos.getAllVehicules();
        if (vehicules.isEmpty()) {
            System.out.println("Vehicules n'existe pas");
        }
        else {
            for (Vehicule vehicule : vehicules) {
                System.out.println(vehicule.toString());
            }
        }

    }

    public void modifierVehicule(Vehicule s){
        vehiculeRepos.modifierVehicule(s);
    }
}