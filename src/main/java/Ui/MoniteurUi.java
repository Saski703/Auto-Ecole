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
    MoniteurController moniteurController = new MoniteurController();
    SeanceController seanceController = new SeanceController();

    // -------------------- Menu -------------------
    public void Menu() {
        System.out.println("========================================");
        System.out.println("        🧑‍🏫 MENU MONITEUR - SYSTEME        ");
        System.out.println("========================================");
        System.out.println("[1] ▶ Ajouter un moniteur");
        System.out.println("[2] ▶ Supprimer un moniteur");
        System.out.println("[3] ▶ Rechercher un moniteur");
        System.out.println("[4] ▶ Modifier un moniteur");
        System.out.println("[5] ▶ Afficher tous les moniteurs");
        System.out.println("[6] ▶ Calculer le salaire d'un moniteur");
        System.out.println("[7] ▶ Afficher planning (semaine)");
        System.out.println("[8] ▶ Retour");
        System.out.println("----------------------------------------");
        System.out.print("👉 Votre choix : ");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch (choice) {
            case 1: ajoutMoniteur(); break;
            case 2: suppressionMoniteur(); break;
            case 3: rechercheMoniteur(); break;
            case 4: modifierMoniteur(); break;
            case 5: afficherMoniteurs(); break;
            case 6: calculerSalaire(); break;
            case 7: afficherPlanningMoniteur(); break;
            case 8:
                System.out.println("👋 Retour au menu principal...");
                return;
            default:
                System.out.println("❌ Choix invalide, veuillez réessayer.");
        }
        Menu();
    }

    // ----------------------- AJOUT -----------------------
    public void ajoutMoniteur() {
        System.out.println("===== ➕ AJOUT MONITEUR =====");
        Scanner sc = new Scanner(System.in);

        System.out.print("CIN : ");
        int cin = sc.nextInt();
        if (moniteurController.rechercheMoniteur(cin) != null) {
            System.out.println("❌ Ce numéro CIN existe déjà.");
            return;
        }

        System.out.print("Nom : ");
        String nom = sc.next();
        System.out.print("Prénom : ");
        String prenom = sc.next();
        System.out.print("Prix code : ");
        double prixCode = sc.nextDouble();
        System.out.print("Prix conduite : ");
        double prixConduit = sc.nextDouble();

        Moniteur m = new Moniteur(cin, nom, prenom, prixCode, prixConduit);
        moniteurController.ajoutMoniteur(m);
        System.out.println("✔ Moniteur ajouté avec succès !");
    }

    // ----------------------- MODIFIER -----------------------
    public void modifierMoniteur() {
        System.out.println("===== ✏ MODIFIER MONITEUR =====");
        Scanner sc = new Scanner(System.in);

        System.out.print("CIN du moniteur : ");
        int cin = sc.nextInt();
        Moniteur m = moniteurController.rechercheMoniteur(cin);

        if (m != null) {
            System.out.println(m);
            String nom = m.getNom();
            String prenom = m.getPrenom();
            double prixCode = m.getPrixCode();
            double prixConduit = m.getPrixConduit();

            System.out.print("Modifier nom ? (y/n) ");
            if (sc.next().equals("y")) {
                System.out.print("Nouveau nom : ");
                nom = sc.next();
            }

            System.out.print("Modifier prénom ? (y/n) ");
            if (sc.next().equals("y")) {
                System.out.print("Nouveau prénom : ");
                prenom = sc.next();
            }

            System.out.print("Modifier prix code ? (y/n) ");
            if (sc.next().equals("y")) {
                System.out.print("Nouveau prix : ");
                prixCode = sc.nextDouble();
            }

            System.out.print("Modifier prix conduite ? (y/n) ");
            if (sc.next().equals("y")) {
                System.out.print("Nouveau prix : ");
                prixConduit = sc.nextDouble();
            }

            Moniteur m1 = new Moniteur(cin, nom, prenom, prixCode, prixConduit);
            moniteurController.suppressionMoniteur(cin);
            moniteurController.ajoutMoniteur(m1);
            System.out.println("✔ Modification effectuée avec succès !");

        } else {
            System.out.println("❌ Moniteur introuvable.");
        }
    }

    // ----------------------- SUPPRESSION -----------------------
    public void suppressionMoniteur() {
        System.out.println("===== 🗑 SUPPRESSION MONITEUR =====");
        Scanner sc = new Scanner(System.in);

        System.out.print("CIN : ");
        int cin = sc.nextInt();

        boolean v = moniteurController.suppressionMoniteur(cin);

        System.out.println(v ? "✔ Moniteur supprimé." : "❌ Moniteur introuvable.");
    }

    // ----------------------- RECHERCHE -----------------------
    public void rechercheMoniteur() {
        System.out.println("===== 🔍 RECHERCHER MONITEUR =====");
        Scanner sc = new Scanner(System.in);

        System.out.print("CIN : ");
        int cin = sc.nextInt();

        Moniteur m = moniteurController.rechercheMoniteur(cin);

        System.out.println(m != null ? m : "❌ Moniteur introuvable.");
    }

    // -------------------- Afficher Tous--------------------
    public void afficherMoniteurs() {
        System.out.println("===== 📋 LISTE DES MONITEURS =====");
        moniteurController.afficherMoniteurs();
    }

    // -------------------- Calcule de Salaire--------------------
    public void calculerSalaire() {
        System.out.println("===== 💰 CALCUL SALAIRE =====");
        Scanner sc = new Scanner(System.in);

        System.out.print("CIN : ");
        int cin = sc.nextInt();

        System.out.print("Mois (1-12): ");
        int mois = sc.nextInt();
        System.out.print("Année (ex: 2025): ");
        int annee = sc.nextInt();

        double s = moniteurController.calculerSalaire(cin, annee, mois);
        System.out.println(s != -1 ? "Salaire : " + s : "❌ Moniteur introuvable.");
    }

    //----------------------Planning Moniteur/Semaine----------------
    public void afficherPlanningMoniteur() {
        Scanner sc = new Scanner(System.in);
        System.out.println("===== 🗓 PLANNING MONITEUR =====");

        System.out.print("CIN : ");
        int cin = sc.nextInt();
        Moniteur m = moniteurController.rechercheMoniteur(cin);

        if (m == null) {
            System.out.println("❌ Moniteur introuvable.");
            return;
        }

        System.out.print("Date référence (yyyy-MM-dd) : ");
        LocalDate refDate = LocalDate.parse(sc.next());

        LocalDate start = refDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate end = refDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        System.out.println("Planning du " + start + " au " + end);
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-12s %-8s %-10s %-15s\n", "DATE", "HEURE", "TYPE", "VÉHICULE");
        System.out.println("------------------------------------------------------------");

        for (Seance seance : seanceController.getAllSeances()) {
            if (seance.getMoniteur().getCin() == cin &&
                    !seance.getDate().isBefore(start) &&
                    !seance.getDate().isAfter(end)) {

                String vehiculeInfo = "---";
                if (seance instanceof SeanceConduit) {
                    vehiculeInfo = ((SeanceConduit) seance).getVehicule().getMat();
                }

                System.out.printf("%-12s %-8s %-10s %-15s\n",
                        seance.getDate(),
                        seance.getHeure(),
                        seance.getType(),
                        vehiculeInfo);
            }
        }
    }
}