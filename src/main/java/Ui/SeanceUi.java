package Ui;

import Controllers.SeanceController;
import Controllers.MoniteurController;
import Controllers.CandidatController;
import Controllers.VehiculeController;
import Models.*; // Imports Seance, SeanceCode, SeanceConduite

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Scanner;

public class SeanceUi {

    SeanceController seanceController = new SeanceController();
    MoniteurController moniteurController = new MoniteurController();
    //CandidatController candidatController = new CandidatController();
    VehiculeController vehiculeController = new VehiculeController();

    public void Menu() {
        System.out.println("----- Gestion des Séances -----");
        System.out.println("1. Ajouter une Séance (Code ou Conduite)");
        System.out.println("2. Supprimer Séance");
        System.out.println("3. Afficher TOUTES les Séances");
        System.out.println("4. >> PLANNING SEMAINE (Secrétaire) <<");
        System.out.println("5. Quitter");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice) {
            case 1: ajoutSeance(); Menu(); break;
            case 2: suppressionSeance(); Menu(); break;
            case 3: afficherSeances(); Menu(); break;
            //case 4: afficherPlanningSemaine(); Menu(); break;
            case 5: System.out.println("Retour..."); break;
            default: Menu();
        }
    }

    public void ajoutSeance() {
        System.out.println("----- Nouvelle Séance -----");
        Scanner sc = new Scanner(System.in);
        System.out.println("Numero de Seance:");
        int num = sc.nextInt();
        if(seanceController.rechercherSeance(num) != null) {
            System.out.println("Ce numéro existe déjà.");
            return;
        }

        System.out.println("Type (1: Code, 2: Conduite):");
        int typeChoice = sc.nextInt();

        System.out.println("Date (yyyy-MM-dd):");
        LocalDate date = LocalDate.parse(sc.next());
        System.out.println("Heure (HH:mm):");
        LocalTime heure = LocalTime.parse(sc.next());
        System.out.println("Prix:");
        double prix = sc.nextDouble();

        // Link Moniteur
        System.out.println("Cin Moniteur:");
        int cin = sc.nextInt();
        Moniteur moniteur = moniteurController.rechercheMoniteur(cin);
        if (moniteur == null) { System.out.println("Moniteur introuvable"); return; }

        // Link Candidat
        /*System.out.println("ID Candidat:");
        String cId = sc.next();
        Candidat candidat = candidatController.rechercherCandidat(cId);
        if (candidat == null) { System.out.println("Candidat introuvable"); return; }*/

        Seance seance;

        if (typeChoice == 1) {
            // --- CASE 1: SEANCE CODE ---
            seance = new SeanceCode(num, date, heure, moniteur,prix);

        } else if (typeChoice == 2) {
            // --- CASE 2: SEANCE CONDUITE ---
            System.out.println("Matricule Véhicule:");
            String Mat = sc.next();
            Vehicule v = vehiculeController.rechercherVehicule(Mat);
            if(v == null) { System.out.println("Véhicule introuvable"); return; }

            seance = new SeanceConduit(num, date, heure, moniteur,prix, v);
        } else {
            System.out.println("Type invalide.");
            return;
        }

        // Set common fields and add
        //seance.setCandidat(candidat);
        seanceController.ajoutSeance(seance);
        //System.out.println("Séance de " + seance.getType() + " ajoutée !");
        System.out.println("Seance ajoutee");
    }

    /*public void afficherPlanningSemaine() {
        System.out.println("----- PLANNING DE LA SEMAINE -----");
        Scanner sc = new Scanner(System.in);
        System.out.println("Date de référence (yyyy-MM-dd):");
        LocalDate refDate = LocalDate.parse(sc.next());

        LocalDate start = refDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate end = refDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        System.out.println("Semaine du " + start + " au " + end);
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("%-12s %-8s %-10s %-15s %-15s %-15s\n",
                "DATE", "HEURE", "TYPE", "MONITEUR", "VEHICULE", "CANDIDAT");
        System.out.println("-------------------------------------------------------------------------");

        for (Seance s : seanceController.getAllSeances()) {
            if (!s.getDate().isBefore(start) && !s.getDate().isAfter(end)) {

                String vehiculeInfo = "---";
                // Check if it is a Driving session to get the car
                if (s instanceof SeanceConduite) {
                    vehiculeInfo = ((SeanceConduite) s).getVehicule().getMat();
                }

                System.out.printf("%-12s %-8s %-10s %-15s %-15s %-15s\n",
                        s.getDate(),
                        s.getHeure(),
                        s.getType(), // returns "Code" or "Conduite"
                        s.getMoniteur().getNom(),
                        vehiculeInfo,
                        (s.getCandidat() != null ? s.getCandidat().getNom() : "N/A")
                );
            }
        }
        System.out.println("-------------------------------------------------------------------------");
    }*/

    public void afficherSeances() {
        System.out.println("-------- afficher les seances ------");
        seanceController.afficherSeances();
    }

    public void suppressionSeance() {
        System.out.println("----- Suppression de seance -----");
        System.out.println("Numéro de Séance:");
        Scanner sc = new Scanner(System.in);
        boolean v = seanceController.suppressionSeance(sc.nextInt());
        if (v) {
            System.out.println("Seance supprimé");
        } else {
            System.out.println("Seance introuvable");
        }
    }
}