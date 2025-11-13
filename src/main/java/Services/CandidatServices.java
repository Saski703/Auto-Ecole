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
    public boolean test(String m){
        boolean t = true;
        if(m=""){
            t=false;
        }
        else{
            for (int i = 0; i < text.length(); i++){
                char character = text.charAt(i);
                if (!Character.isLetter(character)){
                    return false;
                }
            }
        }
        return  t;
    }
    public boolean testp(String p){
        if(p=="A1"||p=="A"||p=="B"||p=="B+E"||p=="c"||p=="C+E"||p=="D"||p=="D+E"||p=="D1"||p=="H"){
            return true;
        }
        else return false;
    }
}
