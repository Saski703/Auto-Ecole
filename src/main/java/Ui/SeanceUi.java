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

    private SeanceController seanceController = new SeanceController();
    private MoniteurController moniteurController = new MoniteurController();
    private CandidatController candidatController = new CandidatController();
    private VehiculeController vehiculeController = new VehiculeController();
    private Scanner sc = new Scanner(System.in);

    public void Menu() {
        while (true) {
            System.out.println("\n========================================");
            System.out.println("        📅 MENU SÉANCES           ");
            System.out.println("========================================");
            System.out.println("[1] ▶ Ajouter une Séance (Code/Conduite)");
            System.out.println("[2] ▶ Supprimer une Séance");
            System.out.println("[3] ▶ Modifier une Séance");
            System.out.println("[4] ▶ Afficher TOUTES les Séances");
            System.out.println("[5] ▶ PLANNING SEMAINE (Secrétaire)");
            System.out.println("[6] ▶ Retour");
            System.out.println("----------------------------------------");
            System.out.print("👉 Votre choix : ");

            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1: ajoutSeance(); break;
                    case 2: suppressionSeance(); break;
                    case 3: modifierSeance(); break;
                    case 4: afficherSeances(); break;
                    case 5: afficherPlanningSemaine(); break;
                    case 6:
                        System.out.println("👋 Retour...");
                        return;
                    default:
                        System.out.println("❌ Choix invalide.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Erreur : Veuillez entrer un chiffre.");
            }
        }
    }

    public void ajoutSeance() {
        System.out.println("\n===== ➕ NOUVELLE SÉANCE =====");
        try {
            // 1. Numéro
            System.out.print("Numéro de Séance : ");
            int num = Integer.parseInt(sc.nextLine());
            if(seanceController.rechercherSeance(num) != null) {
                System.out.println("❌ Ce numéro de séance existe déjà.");
                return;
            }

            // 2. Type
            System.out.print("Type (1: Code, 2: Conduite) : ");
            int typeChoice = Integer.parseInt(sc.nextLine());
            if (typeChoice != 1 && typeChoice != 2) {
                System.out.println("❌ Type invalide.");
                return;
            }

            // 3. Date & Heure
            System.out.print("Date (yyyy-MM-dd) : ");
            LocalDate date = LocalDate.parse(sc.nextLine());

            System.out.print("Heure (HH:mm) : ");
            LocalTime heure = LocalTime.parse(sc.nextLine());

            // 4. Moniteur & Disponibilité
            System.out.print("CIN Moniteur : ");
            int cinMoniteur = Integer.parseInt(sc.nextLine());
            Moniteur moniteur = moniteurController.rechercheMoniteur(cinMoniteur);

            if (moniteur == null) {
                System.out.println("❌ Moniteur introuvable.");
                return;
            }

            // Vérification conflit (F4)
            if (!seanceController.isMoniteurDisponible(moniteur, date, heure)) {
                System.out.println("🚨 CONFLIT : Le moniteur " + moniteur.getNom() + " est déjà occupé ce jour-là à " + heure + " !");
                return;
            }

            // 5. Candidat
            System.out.print("CIN Candidat : ");
            int cinCandidat = Integer.parseInt(sc.nextLine());
            Candidat candidat = candidatController.rechercheCandidat(cinCandidat);

            if (candidat == null) {
                System.out.println("❌ Candidat introuvable.");
                return;
            }

            // 6. Prix
            System.out.print("Prix (DT) : ");
            double prix = Double.parseDouble(sc.nextLine());

            // 7. Création de l'objet
            Seance seance = null;

            if (typeChoice == 1) {
                // SEANCE CODE
                // Note: Assurez-vous que votre modèle SeanceCode a bien ce constructeur avec Candidat
                seance = new SeanceCode(num, date, heure, moniteur, candidat, prix);
            } else {
                // SEANCE CONDUITE
                System.out.print("Matricule Véhicule : ");
                String mat = sc.nextLine();
                Vehicule v = vehiculeController.rechercherVehicule(mat);

                if(v == null) {
                    System.out.println("❌ Véhicule introuvable.");
                    return;
                }

                // Note: Assurez-vous que votre modèle SeanceConduit a bien ce constructeur avec Candidat
                seance = new SeanceConduit(num, date, heure, moniteur, candidat, prix, v);
            }

            seanceController.ajoutSeance(seance);
            System.out.println("✅ Séance ajoutée avec succès !");

        } catch (Exception e) {
            System.out.println("❌ Erreur de saisie (Format incorrect). Détails: " + e.getMessage());
        }
    }

    public void modifierSeance() {
        System.out.println("\n===== ✏ MODIFIER SÉANCE =====");
        System.out.print("Numéro de la séance à modifier : ");
        try {
            int num = Integer.parseInt(sc.nextLine());
            Seance s = seanceController.rechercherSeance(num);

            if (s == null) {
                System.out.println("❌ Séance introuvable.");
                return;
            }

            System.out.println("--- Détails Actuels ---");
            System.out.println(s.toString());
            System.out.println("-----------------------");
            System.out.println("(Laissez vide et appuyez sur Entrée pour ne pas changer)");

            // 1. Modifier Date
            System.out.print("Nouvelle Date (" + s.getDate() + ") : ");
            String dateStr = sc.nextLine();
            if (!dateStr.isEmpty()) {
                s.setDate(LocalDate.parse(dateStr));
            }

            // 2. Modifier Heure
            System.out.print("Nouvelle Heure (" + s.getHeure() + ") : ");
            String heureStr = sc.nextLine();
            if (!heureStr.isEmpty()) {
                s.setHeure(LocalTime.parse(heureStr));
            }

            // 3. Modifier Moniteur
            String nomMoniteur = (s.getMoniteur() != null) ? s.getMoniteur().getNom() : "Aucun";
            System.out.print("Nouveau Moniteur CIN (" + nomMoniteur + ") : ");
            String moniteurStr = sc.nextLine();
            if (!moniteurStr.isEmpty()) {
                Moniteur m = moniteurController.rechercheMoniteur(Integer.parseInt(moniteurStr));
                if (m != null) {
                    // Check disponibilité simple (optionnel, attention aux conflits avec soi-même)
                    if (seanceController.isMoniteurDisponible(m, s.getDate(), s.getHeure())) {
                        s.setMoniteur(m);
                    } else {
                        System.out.println("⚠️ Attention : Le moniteur semble occupé, changement appliqué quand même.");
                        s.setMoniteur(m);
                    }
                } else {
                    System.out.println("❌ Moniteur introuvable, inchangé.");
                }
            }

            // 4. Modifier Prix
            System.out.print("Nouveau Prix (" + s.getPrix() + ") : ");
            String prixStr = sc.nextLine();
            if (!prixStr.isEmpty()) {
                s.setPrix(Double.parseDouble(prixStr));
            }

            // 5. Modifier Véhicule (Si Conduite)
            if (s instanceof SeanceConduit) {
                SeanceConduit scd = (SeanceConduit) s;
                String matVehicule = (scd.getVehicule() != null) ? scd.getVehicule().getMat() : "Aucun";
                System.out.print("Nouveau Véhicule Matricule (" + matVehicule + ") : ");
                String vehiculeStr = sc.nextLine();
                if (!vehiculeStr.isEmpty()) {
                    Vehicule v = vehiculeController.rechercherVehicule(vehiculeStr);
                    if (v != null) scd.setVehicule(v);
                    else System.out.println("❌ Véhicule introuvable, inchangé.");
                }
            }


            seanceController.modifierSeance(s);
            System.out.println("✅ Séance modifiée (Si implémenté dans le contrôleur).");

        } catch (Exception e) {
            System.out.println("❌ Erreur de saisie : " + e.getMessage());
        }
    }

    public void afficherPlanningSemaine() {
        System.out.println("\n===== 🗓 PLANNING DE LA SEMAINE =====");
        try {
            System.out.print("Date de référence (yyyy-MM-dd) [Entrée pour aujourd'hui] : ");
            String dateStr = sc.nextLine();
            LocalDate refDate = dateStr.isEmpty() ? LocalDate.now() : LocalDate.parse(dateStr);

            LocalDate start = refDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            LocalDate end = refDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

            System.out.println("Semaine du " + start + " au " + end);
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.printf("%-12s %-8s %-10s %-15s %-15s %-15s\n",
                    "DATE", "HEURE", "TYPE", "MONITEUR", "VÉHICULE", "CANDIDAT");
            System.out.println("--------------------------------------------------------------------------------------");

            boolean empty = true;
            for (Seance s : seanceController.getAllSeances()) {
                if (!s.getDate().isBefore(start) && !s.getDate().isAfter(end)) {

                    String vehiculeInfo = "-";
                    String candidatNom = (s.getCandidat() != null) ? s.getCandidat().getNom() : "Inconnu";
                    String moniteurNom = (s.getMoniteur() != null) ? s.getMoniteur().getNom() : "Inconnu";

                    if (s instanceof SeanceConduit) {
                        Vehicule v = ((SeanceConduit) s).getVehicule();
                        if (v != null) vehiculeInfo = v.getMat();
                    }

                    System.out.printf("%-12s %-8s %-10s %-15s %-15s %-15s\n",
                            s.getDate(),
                            s.getHeure(),
                            s.getType(),
                            moniteurNom,
                            vehiculeInfo,
                            candidatNom
                    );
                    empty = false;
                }
            }
            if(empty) System.out.println("                        (Aucune séance cette semaine)");
            System.out.println("--------------------------------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println("❌ Format de date invalide.");
        }
    }

    public void afficherSeances() {
        System.out.println("\n===== 📋 TOUTES LES SÉANCES =====");
        seanceController.afficherSeances();
    }

    public void suppressionSeance() {
        System.out.println("\n===== 🗑 SUPPRESSION SÉANCE =====");
        System.out.print("Numéro de Séance : ");
        try {
            int num = Integer.parseInt(sc.nextLine());
            boolean v = seanceController.suppressionSeance(num);
            System.out.println(v ? "✅ Séance supprimée." : "❌ Séance introuvable.");
        } catch (NumberFormatException e) {
            System.out.println("❌ Numéro invalide.");
        }
    }
}