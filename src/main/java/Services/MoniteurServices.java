package Services;

import Models.Moniteur;
import Repositories.MoniteurRepositories;
import Ui.MoniteurUi;

public class MoniteurServices {
    MoniteurRepositories moniteurRepositories = new MoniteurRepositories();

    public void ajoutMoniteur(Moniteur m) {
        moniteurRepositories.ajoutMoniteur(m);
    }

    public Moniteur rechercheMoniteur(int cin) {
        return moniteurRepositories.rechercheMoniteur(cin);
    }

    public boolean suppressionMoniteur(int cin) {
        return moniteurRepositories.suppressionMoniteur(cin);
    }
}
