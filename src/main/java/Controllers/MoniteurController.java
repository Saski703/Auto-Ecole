package Controllers;

import Models.Moniteur;
import Services.MoniteurServices;
import Ui.MoniteurUi;

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
        System.out.println("-----Afficher Moniteurs-----");
        moniteurServices.afficherMoniteurs();
    }

    public int calculerSalaire(int cin) {
        return moniteurServices.calculerSalaire(cin);
    }
}