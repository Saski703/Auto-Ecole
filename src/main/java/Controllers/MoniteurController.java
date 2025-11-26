package Controllers;

import Models.Moniteur;
import Models.Seance;
import Services.MoniteurServices;
import Ui.MoniteurUi;

import java.time.LocalDate;
import java.util.List;

public class MoniteurController {

    MoniteurServices moniteurServices = new MoniteurServices();

    public void init(){
        MoniteurUi moniteurUi = new MoniteurUi();
        moniteurUi.Menu();
    }

    public void ajoutMoniteur(Moniteur m) {
        moniteurServices.ajoutMoniteur(m);
    }

    public Moniteur rechercheMoniteur(int cin) {
        return moniteurServices.rechercheMoniteur(cin);
    }

    public boolean suppressionMoniteur(int cin) {
        return moniteurServices.suppressionMoniteur(cin);
    }

    public void afficherMoniteurs() {
        moniteurServices.afficherMoniteurs();
    }

    public double calculerSalaire(int cin, int annee, int mois) {
        return moniteurServices.calculerSalaire(cin, annee, mois);
    }

}