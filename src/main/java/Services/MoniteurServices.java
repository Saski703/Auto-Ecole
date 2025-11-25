package Services;

import Models.Moniteur;
import Models.Seance;
import Repositories.MoniteurRepositories;
import Ui.MoniteurUi;
import java.time.DayOfWeek;
import java.time.LocalDate;
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

    public int calculerSalaire(int cin) {
        return -1;
    }

}
