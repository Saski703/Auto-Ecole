package Controllers;

import Models.Moniteur;
import Models.Seance;
import Services.SeanceServices;
import Ui.MoniteurUi;
import Ui.SeanceUi;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;

public class SeanceController {
    SeanceServices seanceServices =  new SeanceServices();

    public void init(){
        SeanceUi seanceUi = new SeanceUi();
        seanceUi.Menu();
    }

    public Seance rechercherSeance(int num){
        return null;
    }

    public void ajoutSeance(Seance seance){
        seanceServices.ajoutSeance(seance);
    }

    public void afficherSeances(){
        seanceServices.afficherSeances();
    }

    public boolean suppressionSeance(int num){
        return seanceServices.suppressionSeance(num);
    }

    public List<Seance> getAllSeances(){
        return seanceServices.getAllSeances();
    }

    public boolean isMoniteurDisponible(Moniteur moniteur, LocalDate date, LocalTime heure){
        return seanceServices.isMoniteurDisponible(moniteur, date, heure);
    }
}
