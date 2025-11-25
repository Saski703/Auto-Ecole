package Services;

import Models.Moniteur;
import Models.Seance;
import Models.SeanceCode;
import Repositories.SeanceRepositories;

import java.util.List;

public class SeanceServices {
    SeanceRepositories seanceRepositories =  new SeanceRepositories();
    public Seance rechercherSeance(int num){
        List<Seance> allSeances = seanceRepositories.getAllSeances();

        for (Seance seance : allSeances) {
            if (seance.getNum() == num) {
                return seance;
            }
        }
        return null;
    }

    public void ajoutSeance(Seance seance){
        seanceRepositories.save(seance);
    }

    public void afficherSeances(){
        List<Seance> seanceList = seanceRepositories.getAllSeances();
        if(seanceList.isEmpty()){
            System.out.println("Seances n'existe pas");
        }else {
            for (Seance s : seanceList) {
                System.out.println(s.toString());
            }
        }
    }

    public boolean suppressionSeance(int num){
        return seanceRepositories.suppressionSeance(num);
    }

    public List<Seance> getAllSeances(){
        return seanceRepositories.getAllSeances();
    }
}
