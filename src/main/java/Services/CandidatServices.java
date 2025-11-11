package Services;

import Models.Candidat;
import Repositories.CandidatRepositories;

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
}
