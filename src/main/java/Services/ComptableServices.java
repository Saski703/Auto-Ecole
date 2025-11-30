package Services;

import Models.Maintenance;
import Models.Moniteur;
import Models.Seance;
import Models.Vehicule;
import Repositories.MoniteurRepositories;
import Repositories.SeanceRepositories;
import Repositories.VehiculeRepositories;

import java.util.ArrayList;
import java.util.List;

public class ComptableServices {
    SeanceRepositories seanceRepositories =  new SeanceRepositories();
    MoniteurRepositories moniteurRepositories = new MoniteurRepositories();
    MoniteurServices moniteurServices = new MoniteurServices();
    VehiculeRepositories vehiculeRepositories = new VehiculeRepositories();
    public void afficherBilan(int mois, int annee) {
        System.out.println(genererBilanMensuel(mois, annee));
    }

    public double calculerRevenus(int mois, int annee) {
        List<Seance> seanceList = seanceRepositories.getAllSeances();
        double revenu = 0;
        for (Seance seance : seanceList) {
            if (seance.getDate().getYear() == annee && seance.getDate().getMonthValue() == mois) {
                revenu += seance.getPrix();
            }
        }
        return revenu;
    }

    public double calculerDepensesSalaires(int mois,  int annee) {
        List<Moniteur> moniteurList = moniteurRepositories.getAllMoniteurs();
        double depensesSalaire = 0;
        for (Moniteur moniteur : moniteurList) {
            depensesSalaire += moniteurServices.calculerSalaire(moniteur.getCin(), annee, mois);
        }
        return depensesSalaire;
    }

    public double calculerDepensesMaintenance(int mois,  int annee) {
        List<Vehicule> vehiculeList = vehiculeRepositories.getAllVehicules();
        double depensesMaintenance = 0;
        for (Vehicule vehicule : vehiculeList) {
            for (Maintenance m : vehicule.getMaintenance()){
                depensesMaintenance += m.getMontant();
            }
        }
        return depensesMaintenance;
    }

    public String genererBilanMensuel(int mois, int annee) {
        double revenus = calculerRevenus(mois, annee);
        double depensesSalaires = calculerDepensesSalaires(mois, annee);
        double depensesMaintenance = calculerDepensesMaintenance(mois, annee);
        double totalDepenses = depensesSalaires + depensesMaintenance;
        double resultatNet = revenus - totalDepenses;

        String etat = resultatNet >= 0 ? "✅ BÉNÉFICIAIRE" : "🔻 DÉFICITAIRE";

        return String.format("""
            \n======================================================
               📈 BILAN COMPTABLE MENSUEL : %02d/%d
            ======================================================
            
            1. REVENUS (Séances Code & Conduite)
               ---------------------------------------------
               💰 Total Entrées :            + %.3f DT
            
            2. DÉPENSES
               ---------------------------------------------
               👨‍🏫 Salaires Moniteurs :       - %.3f DT
               🛠️  Maintenance Véhicules :    - %.3f DT
               ---------------------------------------------
               💸 Total Sorties :            - %.3f DT
            
            ======================================================
               RÉSULTAT NET :                %.3f DT
               Statut : %s
            ======================================================
            """, mois, annee, revenus, depensesSalaires, depensesMaintenance, totalDepenses, resultatNet, etat);
    }
}
