package Services;

import Models.Moniteur;
import Repositories.MoniteurRepositories;
import Ui.MoniteurUi;

import java.util.List;

public class MoniteurServices {
    MoniteurRepositories moniteurRepositories = new MoniteurRepositories();

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
        System.out.println("-----Afficher Moniteurs-----");
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
