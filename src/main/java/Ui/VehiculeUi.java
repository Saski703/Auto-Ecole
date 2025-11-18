package Ui;

import Controllers.VehiculeControllers;
import Models.Vehicule;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.Scanner;

public class VehiculeUi {

    VehiculeControllers vehiculeControllers = new VehiculeControllers();


    public void Menu() {
        System.out.println("-----Menu Vehicule-----");
        System.out.println("1. Ajout Vehicule");
        System.out.println("2. Modifier Vehicule");
        System.out.println("3. Supprimer Vehicule");
        System.out.println("4. Rechercher Vehicule");
        System.out.println("5. Quitter");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                ajoutVehicule();
                Menu(); // Show menu again
                break;
            case 2:
                modifierVehicule();
                Menu(); // Show menu again
                break;
            case 3:
                suppressionVehicule();
                Menu(); // Show menu again
                break;
            case 4:
                rechercherVehicule();
                Menu();
                break;
            case 5:
                System.out.println("Retour au menu principal...");
                break;
            default:
                System.out.println("Invalid choice");
                Menu();
        }

    }

    public void ajoutVehicule() {
        System.out.println("-----Ajout Vehicule-----");
        Scanner sc = new Scanner(System.in);
        System.out.println("Num Matricule:");
        int numMat = sc.nextInt();

        if(vehiculeControllers.rechercherVehicule(numMat) != null) {
            System.out.println("Un vehicule with this matricule already exists.");
            return;
        }

        System.out.println("Type (ex: voiture, camion):");
        String type = sc.next();
        System.out.println("date");
        String d = sc.next();
        LocalDate date = LocalDate.parse(d);
        System.out.println("Km Totale:");
        double kmTotale = sc.nextDouble();
        System.out.println("date vignette");
        String d1 = sc.next();
        LocalDate dateVignette = LocalDate.parse(d1);
        System.out.println("Date Assurance");
        String d2 = sc.next();
        LocalDate dateAssurance = LocalDate.parse(d2);
        System.out.println("Date visite technique");
        String d3 = sc.next();
        LocalDate dateVisiteTechnique = LocalDate.parse(d3);
        System.out.println("Date Vidange");
        String d4 = sc.next();
        LocalDate dateVidange = LocalDate.parse(d4);
        Vehicule v = new Vehicule(numMat, type,date, kmTotale, dateVignette, dateAssurance, dateVisiteTechnique, dateVidange);
        vehiculeControllers.ajoutVehicule(v);
    }

    public void modifierVehicule() {
        System.out.println("-----Modifier Vehicule-----");
        Scanner sc = new Scanner(System.in);
        System.out.println("Num Matricule du vehicule à modifier:");
        int numMat = sc.nextInt();

        Vehicule v = vehiculeControllers.rechercherVehicule(numMat);

        if (v != null) {
            System.out.println("Vehicule trouvé: " + v.toString());

            // Get current values
            String type = v.getType();
            double kmTotale = v.getKmTotale();
            LocalDate date = v.getDate();
            LocalDate dateVignette = v.getDateVignette();
            LocalDate dateAssurance = v.getDateAssurance();
            LocalDate dateVisiteTechnique = v.getDateVisiteTechnique();
            LocalDate dateVidange = v.getDateVidange();

            System.out.println("Voulez-vous modifier 'type'? (y/n)");
            String response = sc.next();
            if (response.equalsIgnoreCase("y")) {
                System.out.println("Nouveau type: ");
                type = sc.next();
            }

            System.out.println("Voulez-vous modifier 'kmTotale'? (y/n)");
            response = sc.next();
            if (response.equalsIgnoreCase("y")) {
                System.out.println("Nouveau kmTotale: ");
                kmTotale = sc.nextDouble();
            }

            Vehicule vUpdated = new Vehicule(numMat, type, date, kmTotale, dateVignette, dateAssurance, dateVisiteTechnique, dateVidange);

            vehiculeControllers.suppressionVehicule(v.getNumMat());
            vehiculeControllers.ajoutVehicule(vUpdated);

            System.out.println("Vehicule modifié.");

        } else {
            System.out.println("Vehicule inexistant");
        }

    }

    public void suppressionVehicule() {
        System.out.println("-----Supprimer Vehicule-----");
        Scanner sc = new Scanner(System.in);
        System.out.println("Num Matricule:");
        int numMat = sc.nextInt();
        boolean v = vehiculeControllers.suppressionVehicule(numMat);
        if (v) {
            System.out.println("Vehicule supprimé");
        } else {
            System.out.println("Vehicule introuvable");
        }

    }

    public void rechercherVehicule() {
        System.out.println("-----Recherche Vehicule-----");
        Scanner sc = new Scanner(System.in);
        System.out.println("Num Matricule:");
        int numMat = sc.nextInt();
        Vehicule v = vehiculeControllers.rechercherVehicule(numMat);
        if (v != null) {
            System.out.println(v.toString());
            v.afficherAlertes();
        } else {
            System.out.println("Vehicule inexistant");
        }

    }
}