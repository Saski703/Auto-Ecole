package Controllers;
import Models.Candidat;
import Services.CandidatServices;
import Ui.CandidatUi;

public class CandidatControllers {
    CandidatServices candidatServices = new CandidatServices();
    public void init(){
        CandidatUi candidatui = new CandidatUi();
        candidatui.Menu();

    }

    public void ajoutCandidat(Candidat c) {
        candidatServices.ajoutCandidat(c);
    }

    public Candidat rechercheCandidat(int numCin) {
        return candidatServices.recherchreCondidat(numCin);
    }

    public boolean suppressionCandidat(int numCin) {
        return candidatServices.suppressionCandidat(numCin);
    }

    public void afficherLesCandidats(){
        candidatServices.afficherLesCandidats();
    }

}