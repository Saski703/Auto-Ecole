package Ui;

import Controllers.CandidatController;
import Models.Candidat;
import java.util.Scanner;

public class CandidatUi {

    CandidatController controller = new CandidatController();
    Scanner sc = new Scanner(System.in);

    public void Menu() {
        while (true) {
            System.out.println("\n--- GESTION CANDIDATS ---");
            System.out.println("1. Ajouter");
            System.out.println("2. Modifier");
            System.out.println("3. Supprimer");
            System.out.println("4. Rechercher (Détails & Paiement)");
            System.out.println("5. Lister tout");
            System.out.println("6. Retour");
            System.out.print("Choix: ");

            try {
                int choix = Integer.parseInt(sc.nextLine());
                switch (choix) {
                    case 1: ajout(); break;
                    case 2: modifier(); break;
                    case 3: supprimer(); break;
                    case 4: rechercher(); break;
                    case 5: controller.afficherLesCandidats(); break;
                    case 6: return;
                    default: System.out.println("Choix invalide.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erreur: Veuillez entrer un chiffre !");
            }
        }
    }

    public void ajout() {
        System.out.println("\n--- NOUVEAU CANDIDAT ---");
        try {
            System.out.print("CIN (8 chiffres): ");
            int cin = Integer.parseInt(sc.nextLine());
            if (controller.rechercheCandidat(cin) != null) {
                System.out.println("❌ Ce CIN existe déjà !");
                return;
            }

            // --- VALIDATION NOM (Pas de chiffres) ---
            String nom;
            while (true) {
                System.out.print("Nom: ");
                nom = sc.nextLine().trim();
                // Regex: Uniquement lettres (a-z), espaces et tirets
                if (!nom.isEmpty() && nom.matches("[a-zA-Z -]+")) {
                    break;
                }
                System.out.println("❌ Le nom ne doit contenir que des lettres (ex: Ben Ali).");
            }

            // --- VALIDATION PRENOM (Pas de chiffres) ---
            String prenom;
            while (true) {
                System.out.print("Prénom: ");
                prenom = sc.nextLine().trim();
                if (!prenom.isEmpty() && prenom.matches("[a-zA-Z -]+")) {
                    break;
                }
                System.out.println("❌ Le prénom ne doit contenir que des lettres.");
            }

            System.out.print("Adresse: ");
            String adr = sc.nextLine();

            System.out.print("Téléphone (8 chiffres): ");
            int tel = Integer.parseInt(sc.nextLine());

            // --- VALIDATION PERMIS ---
            String permis;
            while(true) {
                System.out.print("Type Permis (A, B, C...): ");
                permis = sc.nextLine().toUpperCase();
                if(permis.matches("[A-Z0-9+]+")) break;
                System.out.println("❌ Format invalide.");
            }

            Candidat c = new Candidat(cin, nom, prenom, adr, tel, permis);

            // Prix approximatif (F1)
            if(permis.contains("C") || permis.contains("D")) c.setTotal(2000);
            else c.setTotal(1200);

            controller.ajoutCandidat(c);
            System.out.println("✅ Candidat ajouté.");

        } catch (NumberFormatException e) {
            System.out.println("❌ Erreur: CIN et Téléphone doivent être des chiffres.");
        }
    }

    public void modifier() {
        System.out.print("\nCIN du candidat à modifier: ");
        try {
            int cin = Integer.parseInt(sc.nextLine());
            Candidat c = controller.rechercheCandidat(cin);

            if (c == null) {
                System.out.println("❌ Introuvable.");
                return;
            }

            System.out.println("Laissez vide et tapez Entrée pour ne pas changer.");

            // --- MODIF NOM ---
            while(true) {
                System.out.print("Nom (" + c.getNom() + "): ");
                String nom = sc.nextLine().trim();
                if (nom.isEmpty()) break; // Pas de changement
                if (nom.matches("[a-zA-Z -]+")) {
                    c.setNom(nom);
                    break;
                }
                System.out.println("❌ Invalide (Lettres uniquement).");
            }

            // --- MODIF PRENOM ---
            while(true) {
                System.out.print("Prénom (" + c.getPrenom() + "): ");
                String prenom = sc.nextLine().trim();
                if (prenom.isEmpty()) break;
                if (prenom.matches("[a-zA-Z -]+")) {
                    c.setPrenom(prenom);
                    break;
                }
                System.out.println("❌ Invalide (Lettres uniquement).");
            }

            System.out.print("Téléphone (" + c.getTelephone() + "): ");
            String telStr = sc.nextLine();
            if (!telStr.isEmpty()) c.setTelephone(Integer.parseInt(telStr));

            System.out.print("Adresse (" + c.getAdresse() + "): ");
            String adr = sc.nextLine();
            if (!adr.isEmpty()) c.setAdresse(adr);

            controller.modifierCandidat(c);
            System.out.println("✅ Modifications enregistrées.");

        } catch (Exception e) {
            System.out.println("❌ Erreur de saisie.");
        }
    }

    public void supprimer() {
        System.out.print("\nCIN à supprimer: ");
        try {
            int cin = Integer.parseInt(sc.nextLine());
            if (controller.suppressionCandidat(cin)) System.out.println("🗑️ Candidat supprimé.");
            else System.out.println("❌ Introuvable.");
        } catch (Exception e) { System.out.println("❌ CIN invalide."); }
    }

    public void rechercher() {
        System.out.print("\nCIN recherché: ");
        try {
            int cin = Integer.parseInt(sc.nextLine());
            Candidat c = controller.rechercheCandidat(cin);
            if (c != null) {
                System.out.println("--------------------------------");
                System.out.println(c.toString());
                System.out.println("💰 PRIX TOTAL: " + c.getTotal() + " DT");
                System.out.println("✅ PAYÉ:       " + c.getPaye() + " DT");
                System.out.println("⚠️ RESTE:      " + (c.getTotal() - c.getPaye()) + " DT");
                System.out.println("--------------------------------");
            } else { System.out.println("❌ Introuvable."); }
        } catch (Exception e) { System.out.println("❌ Erreur."); }
    }
}