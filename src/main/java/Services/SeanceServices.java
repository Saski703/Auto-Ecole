package Services;

import Models.Moniteur;
import Models.Seance;
import Models.SeanceCode;
import Repositories.SeanceRepositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class SeanceServices {
    SeanceRepositories seanceRepositories =  new SeanceRepositories();
    public Seance rechercherSeance(int num){
        List<Seance> allSeances = seanceRepositories.getAllSeances();

        for (Seance seance : allSeances) {
            if (seance.getNum() == num) {
                return seance;
            }
        }
        return null;
    }

    public void ajoutSeance(Seance seance){
        seanceRepositories.save(seance);
    }

    public void afficherSeances(){
        List<Seance> seanceList = seanceRepositories.getAllSeances();
        if(seanceList.isEmpty()){
            System.out.println("Seances n'existe pas");
        }else {
            for (Seance s : seanceList) {
                System.out.println(s.toString());
            }
        }
    }

    public boolean suppressionSeance(int num){
        return seanceRepositories.suppressionSeance(num);
    }

    public List<Seance> getAllSeances(){
        return seanceRepositories.getAllSeances();
    }

    public boolean isMoniteurDisponible(Moniteur moniteur, LocalDate date, LocalTime heure){
        List<Seance> allSeances = getAllSeances();
        for (Seance seance : allSeances) {
            if (
                    seance.getMoniteur().getCin() ==  moniteur.getCin() &&
                    seance.getDate().equals(date)
            )
            {
                long diffMinutes = ChronoUnit.MINUTES.between(seance.getHeure(), heure);
                if (Math.abs(diffMinutes) < 60) {
                    return false;
                }
            }
        }
        return true;
    }

    public void modifierSeance(Seance s){
        seanceRepositories.modifierSeance(s);
    }
}
