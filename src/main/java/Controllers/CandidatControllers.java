package Controllers;
import Models.Candidat;
import Services.CandidatServices;
import Ui.CondidatUi;

public class CandidatControllers {
    CandidatServices candidatServices = new CandidatServices();
    public void init(){
        CondidatUi condidatui = new CondidatUi();
        condidatui.Menu();

    }

    public void ajoutCandidat(Candidat c) {
        candidatServices.ajoutCandidat(c);
    }

    public Candidat recherchreCandidat(int numCin) {
        return candidatServices.recherchreCondidat(numCin);
    }

    public boolean suppressionCandidat(int numCin) {
        return candidatServices.suppressionCandidat(numCin);
    }
    public boolean test(String m){
        return candidatServices.test(m);
    }
    public boolean testp(String p){
        return candidatServices.test(p);
    }

}