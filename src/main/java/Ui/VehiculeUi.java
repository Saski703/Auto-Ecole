package Ui;

import Controllers.VehiculeController;
import Models.Maintenance;
import Models.Vehicule;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VehiculeUi {

    private VehiculeController vehiculeController = new VehiculeController();
    private Scanner sc = new Scanner(System.in);

    public void Menu() {
        while (true) {
            System.out.println("\n========================================");
            System.out.println("        🚐 MENU VÉHICULES        ");
            System.out.println("========================================");
            System.out.println("[1] ▶ Ajouter un Véhicule");
            System.out.println("[2] ▶ Modifier un Véhicule");
            System.out.println("[3] ▶ Supprimer un Véhicule");
            System.out.println("[4] ▶ Rechercher un Véhicule");
            System.out.println("[5] ▶ Afficher le Parc (Liste)");
            System.out.println("[6] ▶ TABLEAU DE BORD (Alertes)");
            System.out.println("[7] ▶ Saisir Maintenance / Facture");
            System.out.println("[8] ▶ Afficher Historique Maintenance");
            System.out.println("[9] ▶ Retour");
            System.out.println("----------------------------------------");
            System.out.print("👉 Votre choix : ");

            try {
                String input = sc.nextLine();
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1: ajoutVehicule(); break;
                    case 2: modifierVehicule(); break;
                    case 3: suppressionVehicule(); break;
                    case 4: rechercherVehicule(); break;
                    case 5: afficherVehicules(); break;
                    case 6: afficherTableauDeBoard(); break;
                    case 7: SaisirMaintenance(); break;
                    case 8: afficherHisMaint(); break;
                    case 9:
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

    // --- 1. AJOUT SÉCURISÉ ---
    public void ajoutVehicule() {
        System.out.println("\n===== ➕ AJOUT VÉHICULE =====");

        // 1. Matricule (Unique)
        String mat;
        while (true) {
            mat = lireChaine("Matricule (ex: 123 TN 4567) : ");
            if (vehiculeController.rechercherVehicule(mat) == null) break;
            System.out.println("❌ Ce matricule existe déjà.");
        }

        // 2. Type & Infos de base
        String type = lireChaine("Type (Voiture, Camion...) : ");
        LocalDate dateService = lireDate("Date Mise en Service (yyyy-MM-dd) : ");
        double km = lireDouble("Kilométrage Actuel : ");
        int age;
        do{
            System.out.println("Age");
            age = Integer.parseInt(sc.nextLine());
        }while(age < 0 || age > 30);

        // 3. Papiers (Peuvent être vides si pas encore payés)
        System.out.println("--- Dates Documents (Entrée pour ignorer) ---");
        LocalDate dateVig = lireDateOptionnelle("Date Vignette (yyyy-MM-dd) : ");
        LocalDate dateAss = lireDateOptionnelle("Date Assurance (yyyy-MM-dd) : ");
        LocalDate dateVis = lireDateOptionnelle("Date Visite Technique (yyyy-MM-dd) : ");
        LocalDate dateVid = lireDateOptionnelle("Date Dernière Vidange (yyyy-MM-dd) : ");

        // Création
        Vehicule v = new Vehicule(mat, type, dateService, age, km, dateVig, dateAss, dateVis, dateVid);
        vehiculeController.ajoutVehicule(v);
        System.out.println("✅ Véhicule ajouté avec succès !");
    }

    // --- 2. MODIFICATION SÉCURISÉE (In-Place) ---
    public void modifierVehicule() {
        System.out.println("\n===== ✏ MODIFIER VÉHICULE =====");
        String mat = lireChaine("Matricule du véhicule à modifier : ");
        Vehicule v = vehiculeController.rechercherVehicule(mat);

        if (v == null) {
            System.out.println("❌ Véhicule introuvable.");
            return;
        }

        System.out.println("--- Détails Actuels ---");
        System.out.println(v);
        System.out.println("-----------------------");
        System.out.println("(Laissez vide et appuyez sur Entrée pour ne pas changer)");

        // Type
        String type = lireChaineOptionnelle("Type (" + v.getType() + ") : ");
        if (!type.isEmpty()) v.setType(type);

        // KM
        Double km = lireDoubleOptionnel("Kilométrage (" + v.getKmTotale() + ") : ");
        if (km != null) v.setKmTotale(km);

        // Date Service
        LocalDate ds = lireDateOptionnelle("Date Service (" + v.getDate() + ") : ");
        if (ds != null) v.setDate(ds);

        // Papiers
        LocalDate dv = lireDateOptionnelle("Date Vignette (" + v.getDateVignette() + ") : ");
        if (dv != null) v.setDateVignette(dv);

        LocalDate da = lireDateOptionnelle("Date Assurance (" + v.getDateAssurance() + ") : ");
        if (da != null) v.setDateAssurance(da);

        LocalDate dvt = lireDateOptionnelle("Date Visite Tech (" + v.getDateVisiteTechnique() + ") : ");
        if (dvt != null) v.setDateVisiteTechnique(dvt);

        vehiculeController.modifierVehicule(v);
        System.out.println("✅ Modifications enregistrées.");
    }

    // --- 3. SUPPRESSION ---
    public void suppressionVehicule() {
        System.out.println("\n===== 🗑 SUPPRESSION VÉHICULE =====");
        String mat = lireChaine("Matricule : ");

        if (confirmer("Êtes-vous sûr de vouloir supprimer ce véhicule ?")) {
            boolean deleted = vehiculeController.suppressionVehicule(mat);
            System.out.println(deleted ? "🗑️ Véhicule supprimé." : "❌ Véhicule introuvable.");
        }
    }

    // --- 4. RECHERCHE ---
    public void rechercherVehicule() {
        System.out.println("\n===== 🔍 RECHERCHER VÉHICULE =====");
        String mat = lireChaine("Matricule : ");
        Vehicule v = vehiculeController.rechercherVehicule(mat);

        if (v != null) {
            System.out.println("--------------------------------");
            System.out.println(v.toString());
            System.out.println("--------------------------------");
        } else {
            System.out.println("❌ Véhicule introuvable.");
        }
    }

    // --- 5. LISTE ---
    public void afficherVehicules() {
        System.out.println("\n===== 📋 LISTE DES VÉHICULES =====");
        vehiculeController.afficherVehicules();
    }

    // --- 6. TABLEAU DE BORD ---
    public void afficherTableauDeBoard() {
        System.out.println("\n===== 🔔 TABLEAU DE BORD (ALERTES) =====");
        // Supposons que le contrôleur expose cette méthode via le service
        var alertes = vehiculeController.getAlertes();

        if (alertes.isEmpty()) {
            System.out.println("\n   ✅ TOUT EST EN ORDRE.");
        } else {
            for (String alerte : alertes) {
                if(alerte.contains("🚨")) System.out.println(alerte.toUpperCase());
                else System.out.println(alerte);
            }
        }
        System.out.println("----------------------------------------");
    }

    // --- 7. MAINTENANCE ---
    public void SaisirMaintenance() {
        System.out.println("\n===== 🛠 SAISIE MAINTENANCE =====");
        String mat = lireChaine("Matricule du véhicule : ");
        Vehicule v = vehiculeController.rechercherVehicule(mat);

        if (v == null) {
            System.out.println("❌ Véhicule introuvable.");
            return;
        }

        while (true) {
            System.out.println("\n--- Nouvelle Facture ---");
            String desc = lireChaine("Description (ex: Pneus) : ");
            LocalDate date = lireDate("Date (yyyy-MM-dd) : ");
            double cout = lireDouble("Coût (DT) : ");
            //String preuve = lireChaine("Preuve (Fichier) : ");

            Maintenance m = new Maintenance(desc, date, cout);
            List<Maintenance> listm = new ArrayList<>();
            listm.add(m);
            v.setMaintenance(listm);
            System.out.println("✅ Maintenance ajoutée.");

            if (!confirmer("Ajouter une autre facture pour ce véhicule ?")) break;
        }
        // vehiculeController.modifierVehicule(v); // Sauvegarde
    }

    // --- 8. HISTORIQUE ---
    public void afficherHisMaint() {
        System.out.println("\n===== 📂 HISTORIQUE MAINTENANCE =====");
        String mat = lireChaine("Matricule : ");
        Vehicule v = vehiculeController.rechercherVehicule(mat);

        if (v != null) {
            System.out.println("Historique pour [" + v.getMat() + "] :");
            if (v.getMaintenance().isEmpty()) {
                System.out.println("   (Aucune maintenance enregistrée)");
            } else {
                for (Maintenance m : v.getMaintenance()) {
                    System.out.println("   • " + m.toString());
                }
            }
        } else {
            System.out.println("❌ Véhicule introuvable.");
        }
    }

    // =========================================================
    // 🛡️ MÉTHODES DE VÉRIFICATION ET SAISIE SÉCURISÉE
    // =========================================================

    private String lireChaine(String msg) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println("❌ Erreur : Ce champ ne peut pas être vide.");
        }
    }

    private String lireChaineOptionnelle(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    private double lireDouble(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                double val = Double.parseDouble(sc.nextLine().trim());
                if (val >= 0) return val;
                System.out.println("❌ Le nombre doit être positif.");
            } catch (NumberFormatException e) {
                System.out.println("❌ Erreur : Veuillez entrer un nombre valide (ex: 120.5).");
            }
        }
    }

    private Double lireDoubleOptionnel(String msg) {
        System.out.print(msg);
        String input = sc.nextLine().trim();
        if (input.isEmpty()) return null;
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("❌ Format invalide, valeur ignorée.");
            return null;
        }
    }

    private LocalDate lireDate(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return LocalDate.parse(sc.nextLine().trim());
            } catch (DateTimeParseException e) {
                System.out.println("❌ Erreur : Format de date invalide (Utilisez yyyy-MM-dd).");
            }
        }
    }

    private LocalDate lireDateOptionnelle(String msg) {
        System.out.print(msg);
        String input = sc.nextLine().trim();
        if (input.isEmpty()) return null;
        try {
            return LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            System.out.println("❌ Format invalide, date ignorée (aujourd'hui/null sera utilisé).");
            return null;
        }
    }

    private boolean confirmer(String msg) {
        System.out.print(msg + " (y/n) : ");
        return sc.nextLine().trim().equalsIgnoreCase("y");
    }
}