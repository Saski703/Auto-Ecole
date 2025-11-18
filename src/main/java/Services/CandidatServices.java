package Services;

import Models.Candidat;
import Repositories.CandidatRepositories;

import java.util.List;

public class CandidatServices {
    CandidatRepositories candidatRepositories = new CandidatRepositories();
    public void ajoutCandidat(Candidat c) {
        candidatRepositories.ajoutCandidat(c);
    }
    public Candidat recherchreCondidat(int numCin) {
        return candidatRepositories.recherchreCondidat(numCin);
    }

    public boolean suppressionCandidat(int numCin) {
        return candidatRepositories.suppressionCandidat(numCin);
    }

    public void afficherLesCandidats(){
        List<Candidat> candidats = candidatRepositories.getAllCandidats();

        for (Candidat candidat : candidats) {
            System.out.println(candidat.toString());
        }
    }
}
