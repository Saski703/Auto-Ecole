package Ui;

import Controllers.VehiculeController;
import Models.Maintenance;
import Models.Vehicule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class VehiculeUi {

    VehiculeController vehiculeController = new VehiculeController();


    public void Menu() {
        System.out.println("-----Menu Vehicule-----");
        System.out.println("1. Ajout Vehicule");
        System.out.println("2. Modifier Vehicule");
        System.out.println("3. Supprimer Vehicule");
        System.out.println("4. Rechercher Vehicule");
        System.out.println("5. Afficher Vehicules");
        System.out.println("6. Afficher TABLEAU DE BORD");
        System.out.println("7. Saisir Maintenance");
        System.out.println("8. Afficher L'historique des Maintenances");
        System.out.println("9. Quitter");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                ajoutVehicule();
                break;
            case 2:
                modifierVehicule();
                break;
            case 3:
                suppressionVehicule();
                break;
            case 4:
                rechercherVehicule();
                break;
            case 5:
                afficherVehicules();
                break;
            case 6:
                afficherTableauDeBoard();
                break;
            case 7:
                SaisirMaintenance();
                break;
            case 8:
                afficherHisMaint();
                break;
            case 9:
                System.out.println("Retour au menu principal...");
                return;
            default:
                System.out.println("Invalid choice");
        }
        Menu();

    }

    public void ajoutVehicule() {
        System.out.println("-----Ajout Vehicule-----");
        Scanner sc = new Scanner(System.in);
        System.out.println("Matricule:");
        String Mat = sc.next();

        if(vehiculeController.rechercherVehicule(Mat) != null) {
            System.out.println("Un vehicule with this matricule already exists.");
            return;
        }

        System.out.println("Type (ex: voiture, camion):");
        String type = sc.next();
        System.out.println("date");
        String d = sc.next();
        LocalDate date = LocalDate.parse(d);
        System.out.println("Age : ");
        int age = sc.nextInt();
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
        Vehicule v = new Vehicule(Mat, type,date, age, kmTotale, dateVignette, dateAssurance, dateVisiteTechnique, dateVidange);
        vehiculeController.ajoutVehicule(v);
    }

    public void modifierVehicule() {
        System.out.println("-----Modifier Vehicule-----");
        Scanner sc = new Scanner(System.in);

        System.out.println("Matricule du vehicule à modifier:");
        String Mat = sc.next();  // Matricule is String in your Vehicule class

        Vehicule v = vehiculeController.rechercherVehicule(Mat);

        if (v != null) {
            System.out.println("Vehicule trouvé: " + v.toString());

            // Get current values
            String type = v.getType();
            double kmTotale = v.getKmTotale();
            LocalDate date = v.getDate();
            int age = v.getAge();
            LocalDate dateVignette = v.getDateVignette();
            LocalDate dateAssurance = v.getDateAssurance();
            LocalDate dateVisiteTechnique = v.getDateVisiteTechnique();
            LocalDate dateVidange = v.getDateVidange();

            String response;

            System.out.println("Modifier 'type'? (y/n)");
            response = sc.next();
            if (response.equalsIgnoreCase("y")) {
                System.out.println("Nouveau type: ");
                type = sc.next();
            }

            System.out.println("Modifier 'kmTotale'? (y/n)");
            response = sc.next();
            if (response.equalsIgnoreCase("y")) {
                System.out.println("Nouveau kmTotale: ");
                kmTotale = sc.nextDouble();
            }

            System.out.println("Modifier 'date' (date d'achat)? (y/n)");
            response = sc.next();
            if (response.equalsIgnoreCase("y")) {
                System.out.println("Nouvelle date (yyyy-MM-dd): ");
                date = LocalDate.parse(sc.next());
            }

            System.out.println("Modifier 'age'? (y/n)");
            response = sc.next();
            if (response.equalsIgnoreCase("y")) {
                System.out.println("Nouvel age: ");
                age = sc.nextInt();
            }

            System.out.println("Modifier 'dateVignette'? (y/n)");
            response = sc.next();
            if (response.equalsIgnoreCase("y")) {
                System.out.println("Nouvelle date vignette (yyyy-MM-dd): ");
                dateVignette = LocalDate.parse(sc.next());
            }

            System.out.println("Modifier 'dateAssurance'? (y/n)");
            response = sc.next();
            if (response.equalsIgnoreCase("y")) {
                System.out.println("Nouvelle date assurance (yyyy-MM-dd): ");
                dateAssurance = LocalDate.parse(sc.next());
            }

            System.out.println("Modifier 'dateVisiteTechnique'? (y/n)");
            response = sc.next();
            if (response.equalsIgnoreCase("y")) {
                System.out.println("Nouvelle date visite technique (yyyy-MM-dd): ");
                dateVisiteTechnique = LocalDate.parse(sc.next());
            }

            System.out.println("Modifier 'dateVidange'? (y/n)");
            response = sc.next();
            if (response.equalsIgnoreCase("y")) {
                System.out.println("Nouvelle date vidange (yyyy-MM-dd): ");
                dateVidange = LocalDate.parse(sc.next());
            }

            // Create updated object
            Vehicule vUpdated = new Vehicule(Mat, type, date, age, kmTotale,
                    dateVignette, dateAssurance, dateVisiteTechnique, dateVidange);

            // Replace old object
            vehiculeController.suppressionVehicule(v.getMat());
            vehiculeController.ajoutVehicule(vUpdated);

            System.out.println("Vehicule modifié.");

        } else {
            System.out.println("Vehicule inexistant");
        }
    }


    public void suppressionVehicule() {
        System.out.println("-----Supprimer Vehicule-----");
        Scanner sc = new Scanner(System.in);
        System.out.println("Matricule:");
        String Mat = sc.next();
        boolean v = vehiculeController.suppressionVehicule(Mat);
        if (v) {
            System.out.println("Vehicule supprimé");
        } else {
            System.out.println("Vehicule introuvable");
        }

    }

    public void rechercherVehicule() {
        System.out.println("-----Recherche Vehicule-----");
        Scanner sc = new Scanner(System.in);
        System.out.println("Matricule:");
        String Mat = sc.next();
        Vehicule v = vehiculeController.rechercherVehicule(Mat);
        if (v != null) {
            System.out.println(v.toString());
        } else {
            System.out.println("Vehicule inexistant");
        }

    }

    public void afficherVehicules(){
        System.out.println("-----Afficher les Vehicules-----");
        vehiculeController.afficherVehicules();
    }

    public void afficherTableauDeBoard(){
        System.out.println("-----Afficher les Tableau de Board-----");
        List<String> alertes = vehiculeController.getAlertes();
        if (alertes.isEmpty()) {
            System.out.println("\n   TOUT EST EN ORDRE.");
            System.out.println("    Aucune maintenance ni papier administratif requis.\n");
        }else {
            for (String alerte : alertes) {
                System.out.println(alerte);
            }
        }
    }

    public void SaisirMaintenance(){
        System.out.println("------------Ajouter Des Maintenances:-------------");
        Scanner sc = new Scanner(System.in);
        System.out.println("Matricule du vehicule:");
        String Mat = sc.next();
        Vehicule v = vehiculeController.rechercherVehicule(Mat);
        if (v != null) {
            List<Maintenance> listM = new ArrayList<>();
            String addMore;
            do {
                System.out.println("Nouveau Maintenance : ");

                System.out.println("Description: ");
                String description = sc.next();
                System.out.println("date");
                String d = sc.next();
                LocalDate date = LocalDate.parse(d);

                Maintenance m = new Maintenance(description, date);
                listM.add(m);
                System.out.println("ADD More?(y/n)");
                addMore = sc.next();
            }while (Objects.equals(addMore, "y"));

            v.setMaintenance(listM);
            //todo
            vehiculeController.suppressionVehicule(v.getMat());
            vehiculeController.ajoutVehicule(v);
        } else {
            System.out.println("Vehicule inexistant");
        }
    }
    public void afficherHisMaint(){
        System.out.println("Afficher les Maintenances");
        System.out.println("====================");

        Scanner sc = new Scanner(System.in);
        System.out.println("    Matricule du vehicule:");
        String Mat = sc.next();
        Vehicule v = vehiculeController.rechercherVehicule(Mat);

        if (v != null) {
            for(Maintenance m : v.getMaintenance()){
                System.out.println(m.toString());
            }
        } else {
            System.out.println("Vehicule inexistant");
        }

        System.out.println("====================");
    }

}