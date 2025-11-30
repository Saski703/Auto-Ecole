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

    // On garde un seul contrôleur et un seul scanner
    private MoniteurController moniteurController = new MoniteurController();
    private SeanceController seanceController = new SeanceController();
    private Scanner sc = new Scanner(System.in);

    public void Menu() {
        while (true) {
            System.out.println("\n--- GESTION MONITEURS ---");
            System.out.println("1. Ajouter Moniteur");
            System.out.println("2. Modifier Moniteur");
            System.out.println("3. Supprimer Moniteur");
            System.out.println("4. Rechercher Moniteur");
            System.out.println("5. Lister tout");
            System.out.println("6. Calculer Salaire");
            System.out.println("7. Planning Semaine");
            System.out.println("8. Retour");
            System.out.print("Choix: ");

            try {
                int choix = Integer.parseInt(sc.nextLine());

                switch (choix) {
                    case 1: ajoutMoniteur(); break;
                    case 2: modifierMoniteur(); break;
                    case 3: suppressionMoniteur(); break;
                    case 4: rechercheMoniteur(); break;
                    case 5: moniteurController.afficherMoniteurs(); break;
                    case 6: calculerSalaire(); break;
                    case 7: afficherPlanningMoniteur(); break;
                    case 8: return;
                    default: System.out.println("Choix invalide.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erreur: Veuillez entrer un chiffre !");
            }
        }
    }

    // --- 1. AJOUT SIMPLE (Comme Candidat) ---
    public void ajoutMoniteur() {
        System.out.println("\n--- NOUVEAU MONITEUR ---");
        try {
            System.out.print("CIN (8 chiffres): ");
            int cin = Integer.parseInt(sc.nextLine());

            if (moniteurController.rechercheMoniteur(cin) != null) {
                System.out.println("❌ Ce CIN existe déjà !");
                return;
            }

            // Validation Nom
            String nom;
            while (true) {
                System.out.print("Nom: ");
                nom = sc.nextLine().trim();
                if (!nom.isEmpty() && nom.matches("[a-zA-Z -]+")) break;
                System.out.println("❌ Le nom ne doit contenir que des lettres.");
            }

            // Validation Prénom
            String prenom;
            while (true) {
                System.out.print("Prénom: ");
                prenom = sc.nextLine().trim();
                if (!prenom.isEmpty() && prenom.matches("[a-zA-Z -]+")) break;
                System.out.println("❌ Le prénom ne doit contenir que des lettres.");
            }

            // Prix avec validation simple
            double prixCode = 0;
            while(true) {
                try {
                    System.out.print("Prix Code/Heure (DT): ");
                    prixCode = Double.parseDouble(sc.nextLine());
                    break;
                } catch(Exception e) { System.out.println("❌ Montant invalide."); }
            }

            double prixConduite = 0;
            while(true) {
                try {
                    System.out.print("Prix Conduite/Heure (DT): ");
                    prixConduite = Double.parseDouble(sc.nextLine());
                    break;
                } catch(Exception e) { System.out.println("❌ Montant invalide."); }
            }

            // Création et Ajout
            Moniteur m = new Moniteur(cin, nom, prenom, prixCode, prixConduite);
            moniteurController.ajoutMoniteur(m);
            System.out.println("✅ Moniteur ajouté avec succès.");

        } catch (NumberFormatException e) {
            System.out.println("❌ Erreur: CIN doit être un chiffre.");
        }
    }

    // --- 2. MODIFICATION (Sans suppression) ---
    public void modifierMoniteur() {
        System.out.print("\nCIN du moniteur à modifier: ");
        try {
            int cin = Integer.parseInt(sc.nextLine());
            Moniteur m = moniteurController.rechercheMoniteur(cin);

            if (m == null) {
                System.out.println("❌ Introuvable.");
                return;
            }

            System.out.println("Laissez vide et tapez Entrée pour ne pas changer.");

            // Nom
            System.out.print("Nom (" + m.getNom() + "): ");
            String nom = sc.nextLine().trim();
            if (!nom.isEmpty() && nom.matches("[a-zA-Z -]+")) m.setNom(nom);

            // Prénom
            System.out.print("Prénom (" + m.getPrenom() + "): ");
            String prenom = sc.nextLine().trim();
            if (!prenom.isEmpty() && prenom.matches("[a-zA-Z -]+")) m.setPrenom(prenom);

            // Prix Code
            System.out.print("Prix Code (" + m.getPrixCode() + "): ");
            String pcStr = sc.nextLine();
            if (!pcStr.isEmpty()) {
                try { m.setPrixCode(Double.parseDouble(pcStr)); }
                catch(Exception e) { System.out.println("❌ Prix ignoré (invalide)."); }
            }

            // Prix Conduite
            System.out.print("Prix Conduite (" + m.getPrixConduit() + "): ");
            String pcondStr = sc.nextLine();
            if (!pcondStr.isEmpty()) {
                try { m.setPrixConduit(Double.parseDouble(pcondStr)); }
                catch(Exception e) { System.out.println("❌ Prix ignoré (invalide)."); }
            }

            moniteurController.modifierMoniteur(m);

            System.out.println("✅ Modifications enregistrées.");

        } catch (Exception e) {
            System.out.println("❌ Erreur de saisie.");
        }
    }

    // --- 3. SUPPRESSION ---
    public void suppressionMoniteur() {
        System.out.print("\nCIN à supprimer: ");
        try {
            int cin = Integer.parseInt(sc.nextLine());
            if (moniteurController.suppressionMoniteur(cin)) {
                System.out.println("🗑️ Moniteur supprimé.");
            } else {
                System.out.println("❌ Introuvable.");
            }
        } catch (Exception e) { System.out.println("❌ CIN invalide."); }
    }

    // --- 4. RECHERCHE ---
    public void rechercheMoniteur() {
        System.out.print("\nCIN recherché: ");
        try {
            int cin = Integer.parseInt(sc.nextLine());
            Moniteur m = moniteurController.rechercheMoniteur(cin);
            if (m != null) {
                System.out.println("--------------------------------");
                System.out.println(m.toString());
                System.out.println("--------------------------------");
            } else { System.out.println("❌ Introuvable."); }
        } catch (Exception e) { System.out.println("❌ Erreur."); }
    }

    // --- 6. SALAIRE ---
    public void calculerSalaire() {
        System.out.println("\n--- SALAIRE ---");
        try {
            System.out.print("CIN Moniteur: ");
            int cin = Integer.parseInt(sc.nextLine());
            if (moniteurController.rechercheMoniteur(cin) == null) {
                System.out.println("❌ Introuvable."); return;
            }

            System.out.print("Mois (1-12): ");
            int mois = Integer.parseInt(sc.nextLine());

            System.out.print("Année: ");
            int annee = Integer.parseInt(sc.nextLine());

            double s = moniteurController.calculerSalaire(cin, annee, mois);
            System.out.println("💰 Salaire estimé : " + s + " DT");

        } catch(Exception e) { System.out.println("❌ Erreur de saisie."); }
    }

    // --- 7. PLANNING ---
    public void afficherPlanningMoniteur() {
        System.out.println("\n--- PLANNING ---");
        try {
            System.out.print("CIN Moniteur: ");
            int cin = Integer.parseInt(sc.nextLine());
            Moniteur m = moniteurController.rechercheMoniteur(cin);
            if (m == null) { System.out.println("❌ Introuvable."); return; }

            System.out.print("Date référence (Entrée pour aujourd'hui): ");
            String dStr = sc.nextLine();
            LocalDate refDate = dStr.isEmpty() ? LocalDate.now() : LocalDate.parse(dStr);

            LocalDate start = refDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            LocalDate end = refDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

            System.out.println("Semaine du " + start + " au " + end);
            System.out.println("------------------------------------------------------");
            System.out.printf("%-12s | %-6s | %-10s | %-15s\n", "DATE", "HEURE", "TYPE", "AUTO");
            System.out.println("------------------------------------------------------");

            for (Seance s : seanceController.getAllSeances()) {
                if (s.getMoniteur() != null && s.getMoniteur().getCin() == cin &&
                        !s.getDate().isBefore(start) && !s.getDate().isAfter(end)) {

                    String auto = "-";
                    if(s instanceof SeanceConduit) {
                        SeanceConduit sc = (SeanceConduit) s;
                        if(sc.getVehicule() != null) auto = sc.getVehicule().getMat();
                    }

                    System.out.printf("%-12s | %-6s | %-10s | %-15s\n",
                            s.getDate(), s.getHeure(), s.getType(), auto);
                }
            }
            System.out.println("------------------------------------------------------");

        } catch(Exception e) { System.out.println("❌ Erreur ou format date incorrect (yyyy-MM-dd)."); }
    }
}