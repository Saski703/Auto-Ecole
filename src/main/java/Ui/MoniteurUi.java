package Ui;

import Controllers.MoniteurController;
import Controllers.SeanceController;
import Models.Moniteur;
import Models.Seance;
import Models.SeanceConduit;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Scanner;

public class MoniteurUi {
    //TODO : Scanner close and Scanner repetetion
    MoniteurController moniteurController = new MoniteurController();
    SeanceController seanceController = new SeanceController();

    // -------------------- Menu -------------------
    public void Menu() {
        System.out.println("-----Menu Moniteur-----");
        System.out.println("    1. Ajout Moniteur");
        System.out.println("    2. Supprimer Moniteur");
        System.out.println("    3. Rechercher Moniteur");
        System.out.println("    4. Modifier Moniteur");
        System.out.println("    5. Affichers tous les moniteurs");
        System.out.println("    6. Calculer le salaire d'un moniteur");
        System.out.println("    7. Afficher planning(semaine)");
        System.out.println("    8. Quitter");

        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez entrer un nombre.");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                ajoutMoniteur();
                break;
            case 2:
                suppressionMoniteur();
                break;
            case 3:
                rechercheMoniteur();
                break;
            case 4:
                modifierMoniteur();
                break;
            case 5:
                afficherMoniteurs();
                break;
            case 6:
                calculerSalaire();
                break;
            case 7:
                afficherPlanningMoniteur();
                break;
            case 8:
                System.out.println("Retour au menu principal...");
                return;
            default:
                System.out.println("Choix invalide, veuillez réessayer.");
        }
        Menu();

    }

    // ----------------------- AJOUT -----------------------
    public void ajoutMoniteur() {
        System.out.println("-----Ajout Moniteur-----");
        Scanner sc = new Scanner(System.in);

        System.out.println("    CIN :");
        int cin = sc.nextInt();
        if(moniteurController.rechercheMoniteur(cin) != null) {
            System.out.println("Ce numéro cin existe déjà.");
            return;
        }
        System.out.println("    Nom :");
        String nom = sc.next();

        System.out.println("    Prenom :");
        String prenom = sc.next();

        System.out.println("    État de disponibilité (true/false) :");
        boolean etat = sc.nextBoolean();

        System.out.println("    Prix Code :");
        double prixCode = sc.nextDouble();

        System.out.println("    Prix Conduit :");
        double prixConduit = sc.nextDouble();

        System.out.println("    Nombre d’heures Code :");
        int nbHCode = sc.nextInt();

        System.out.println("    Nombre d’heures Conduit :");
        int nbHConduit = sc.nextInt();

        Moniteur m = new Moniteur(cin, nom, prenom, etat, prixCode, prixConduit, nbHCode, nbHConduit);
        moniteurController.ajoutMoniteur(m);

        System.out.println("Moniteur ajouté avec succès !");

    }

    // ----------------------- MODIFIER -----------------------
    public void modifierMoniteur() {
        System.out.println("-----Modifier Moniteur-----");
        Scanner sc = new Scanner(System.in);

        System.out.println("CIN du moniteur :");
        int cin = sc.nextInt();

        Moniteur m = moniteurController.rechercheMoniteur(cin);

        if (m != null) {

            System.out.println(m.toString());

            String nom = m.getNom();
            String prenom = m.getPrenom();
            boolean etat = m.getEtat();
            double prixCode = m.getPrixCode();
            double prixConduit = m.getPrixConduit();

            System.out.println("Modifier nom ? (y/n)");
            if (sc.next().equals("y")) {
                System.out.println("Nouveau nom :");
                nom = sc.next();
            }

            System.out.println("Modifier prenom ? (y/n)");
            if (sc.next().equals("y")) {
                System.out.println("Nouveau prénom :");
                prenom = sc.next();
            }

            System.out.println("Modifier disponibilité ? (y/n)");
            if (sc.next().equals("y")) {
                System.out.println("Nouvel état (true/false) :");
                etat = sc.nextBoolean();
            }

            System.out.println("Modifier prix code ? (y/n)");
            if (sc.next().equals("y")) {
                System.out.println("Nouveau prix code :");
                prixCode = sc.nextDouble();
            }

            System.out.println("Modifier prix conduit ? (y/n)");
            if (sc.next().equals("y")) {
                System.out.println("Nouveau prix conduit :");
                prixConduit = sc.nextDouble();
            }

            Moniteur m1 = new Moniteur(cin, nom, prenom, etat, prixCode, prixConduit);
            moniteurController.suppressionMoniteur(cin);
            moniteurController.ajoutMoniteur(m1);
            System.out.println("Modification effectuée avec succès.");

        } else {
            System.out.println("Moniteur introuvable.");
        }

    }
    // ----------------------- SUPPRESSION -----------------------
    public void suppressionMoniteur() {
        System.out.println("-----Supprimer Moniteur-----");
        Scanner sc = new Scanner(System.in);

        System.out.println("CIN :");
        int cin = sc.nextInt();

        boolean v = moniteurController.suppressionMoniteur(cin);

        if (v)
            System.out.println("Moniteur supprimé.");
        else
            System.out.println("Moniteur introuvable.");

    }

    // ----------------------- RECHERCHE -----------------------
    public void rechercheMoniteur() {
        System.out.println("-----Rechercher Moniteur-----");
        Scanner sc = new Scanner(System.in);
        System.out.println("CIN :");
        int cin = sc.nextInt();

        Moniteur m = moniteurController.rechercheMoniteur(cin);

        if (m != null)
            System.out.println(m.toString());
        else
            System.out.println("Moniteur introuvable.");

    }

    // -------------------- Afficher Tous--------------------
    public void afficherMoniteurs() {
        System.out.println("-----Afficher Moniteurs-----");
        moniteurController.afficherMoniteurs();
    }

    // -------------------- Calcule de Salaire--------------------
    public void calculerSalaire() {
        System.out.println("-----Calculer Salaire-----");
        Scanner sc = new Scanner(System.in);
        System.out.println("CIN :");
        int cin = sc.nextInt();

        double s = moniteurController.calculerSalaire(cin);
        if(s != -1) {
            System.out.println("Le salaire est  :" + s);
        }
        else
            System.out.println("Moniteur introuvable.");
    }

    //----------------------Planning Moniteur/Semaine----------------
    public void afficherPlanningMoniteur() {
        Scanner sc = new Scanner(System.in);
        System.out.println("CIN du moniteur :");
        int cin = sc.nextInt();
        Moniteur m = moniteurController.rechercheMoniteur(cin);
        if (m == null){
            System.out.println("Moniteur introuvable.");
            return;
        }
        System.out.println("Date de référence (yyyy-MM-dd) :");
        LocalDate refDate = LocalDate.parse(sc.next());

        // Affichage de l'en-tête du planning
        LocalDate start = refDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate end = refDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        System.out.println("Planning de la semaine pour le moniteur " + cin + " du " + start + " au " + end);
        System.out.println("-------------------------------------------------------------");
        System.out.printf("%-12s %-8s %-10s %-15s\n",//%-15s
                "DATE", "HEURE", "TYPE", "VEHICULE");//, "CANDIDAT");
        System.out.println("-------------------------------------------------------------");

        for (Seance seance : seanceController.getAllSeances()) {
            if (seance.getMoniteur().getCin() == m.getCin()  &&
                    !seance.getDate().isBefore(start) &&
                    !seance.getDate().isAfter(end)) {

                String vehiculeInfo = "---";
                // Check if it is a Driving session to get the car
                if (seance instanceof SeanceConduit) {
                    vehiculeInfo = ((SeanceConduit) seance).getVehicule().getMat();
                }
                System.out.printf("%-12s %-8s %-10s %-15s\n",//%-15s
                        seance.getDate(),
                        seance.getHeure(),
                        seance.getType(),
                        vehiculeInfo);

            }
        }

    }
}