package Services;

import Models.Moniteur;
import Models.Seance;
import Models.SeanceConduit;
import Repositories.MoniteurRepositories;
import Ui.MoniteurUi;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MoniteurServices {
    MoniteurRepositories moniteurRepositories = new MoniteurRepositories();
    SeanceServices seanceServices = new SeanceServices();

    public void ajoutMoniteur(Moniteur m) {
        moniteurRepositories.save(m);
    }

    public Moniteur rechercheMoniteur(int numCin) {
        List<Moniteur> allMoniteurs = moniteurRepositories.getAllMoniteurs();

        for (Moniteur moniteur : allMoniteurs) {
            if (moniteur.getCin() == numCin) {
                return moniteur;
            }
        }
        return null;
    }

    public boolean suppressionMoniteur(int cin) {
        return moniteurRepositories.suppressionMoniteur(cin);
    }

    public void afficherMoniteurs() {
        List<Moniteur> moniteurList = moniteurRepositories.getAllMoniteurs();
        if(moniteurList.isEmpty()){
            System.out.println("Moniteurs n'existe pas");
        }else {
            for (Moniteur m : moniteurList) {
                System.out.println(m.toString());
            }
        }
    }

    public double calculerSalaire(int cin, int annee, int mois) {
        List<Moniteur> moniteurList = moniteurRepositories.getAllMoniteurs();
        Moniteur moniteur = null;

        if(moniteurList.isEmpty()){
            System.out.println("Moniteurs n'existe pas");
        } else {
            for(Moniteur m : moniteurList){
                if(m.getCin() == cin){
                    moniteur = m;
                    break;
                }
            }
        }

        int heuresCode = 0;
        int heuresConduite = 0;
        List<Seance> allSeances = seanceServices.getAllSeances();
        for (Seance seance : allSeances) {
            if(seance.getMoniteur().getCin() == cin && seance.getDate().getYear() == annee && seance.getDate().getMonthValue() == mois) {
                if (seance instanceof SeanceConduit){
                    heuresConduite++;
                }else  {
                    heuresCode++;
                }
            }
        }



        if(moniteur == null){
            return -1;
        }else {
            return (moniteur.getPrixCode() * heuresCode) + (moniteur.getPrixConduit() * heuresConduite);
        }
    }

}
