package Ui;


import Controllers.ComptableController;

import java.time.LocalDate;
import java.util.Scanner;

public class ComptableUi {

    private ComptableController comptableController = new ComptableController();
    private Scanner sc = new Scanner(System.in);

    public void Menu() {
        while(true) {
            System.out.println("\n╔════════════════════════════════════════════════╗");
            System.out.println("║        💰 SUIVI COMPTABLE          ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            System.out.println("1. 📊 Générer le Bilan Mensuel");
            System.out.println("2. ⬅️ Retour");
            System.out.print("👉 Choix: ");

            String choix = sc.nextLine();

            if (choix.equals("1")) {
                afficherBilan();
            } else if (choix.equals("2")) {
                return;
            } else {
                System.out.println("Choix invalide.");
            }
        }
    }

    private void afficherBilan() {
        System.out.println("\n--- Bilan  ---");
        LocalDate today = LocalDate.now();

        System.out.print("Mois (" + today.getMonthValue() + "): ");

        int mois = sc.nextInt();

        System.out.print("Année (" + today.getYear() + "): ");
        int annee = sc.nextInt();

        ComptableController.afficherBilan(mois, annee);


    }
}