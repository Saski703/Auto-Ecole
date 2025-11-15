package Ui;

import Controllers.MoniteurControllers;
import Models.Moniteur;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MoniteurUi {

    MoniteurControllers moniteurControllers = new MoniteurControllers();

    public void Menu() {
        System.out.println("-----Menu Moniteur-----");
        System.out.println("1. Ajout Moniteur");
        System.out.println("2. Supprimer Moniteur");
        System.out.println("3. Rechercher Moniteur");
        System.out.println("4. Modifier Moniteur");
        System.out.println("5. Quitter");

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
                System.out.println("Retour au menu principal...");
                return;
            default:
                System.out.println("Choix invalide, veuillez réessayer.");
        }
        Menu();

    }

    // ----------------------- AJOUT -----------------------
    public void ajoutMoniteur(){
        System.out.println("-----Ajout Moniteur-----");
        Scanner sc = new Scanner(System.in);

        System.out.println("CIN :");
        int cin = sc.nextInt();

        System.out.println("Nom :");
        String nom = sc.next();

        System.out.println("Prenom :");
        String prenom = sc.next();

        System.out.println("État de disponibilité (true/false) :");
        boolean etat = sc.nextBoolean();

        System.out.println("Prix Code :");
        double prixCode = sc.nextDouble();

        System.out.println("Prix Conduit :");
        double prixConduit = sc.nextDouble();

        System.out.println("Nombre d’heures Code :");
        int nbHCode = sc.nextInt();

        System.out.println("Nombre d’heures Conduit :");
        int nbHConduit = sc.nextInt();

        Moniteur m = new Moniteur(cin, nom, prenom, etat, prixCode, prixConduit, nbHCode, nbHConduit);
        moniteurControllers.ajoutMoniteur(m);

        System.out.println("Moniteur ajouté avec succès !");

    }

    // ----------------------- MODIFIER -----------------------
    public void modifierMoniteur(){
        System.out.println("-----Modifier Moniteur-----");
        Scanner sc = new Scanner(System.in);

        System.out.println("CIN du moniteur :");
        int cin = sc.nextInt();

        Moniteur m = moniteurControllers.rechercheMoniteur(cin);

        if(m != null){

            System.out.println(m.toString());

            String nom = m.getNom();
            String prenom = m.getPrenom();
            boolean etat = m.getEtat();
            double prixCode = m.getPrixCode();
            double prixConduit = m.getPrixConduit();
            int nbHcode = m.getNbHeureCode();
            int nbHConduit = m.getNbHeureConduit();

            System.out.println("Modifier nom ? (y/n)");
            if(sc.next().equals("y")){
                System.out.println("Nouveau nom :");
                nom = sc.next();
            }

            System.out.println("Modifier prenom ? (y/n)");
            if(sc.next().equals("y")){
                System.out.println("Nouveau prénom :");
                prenom = sc.next();
            }

            System.out.println("Modifier disponibilité ? (y/n)");
            if(sc.next().equals("y")){
                System.out.println("Nouvel état (true/false) :");
                etat = sc.nextBoolean();
            }

            System.out.println("Modifier prix code ? (y/n)");
            if(sc.next().equals("y")){
                System.out.println("Nouveau prix code :");
                prixCode = sc.nextDouble();
            }

            System.out.println("Modifier prix conduit ? (y/n)");
            if(sc.next().equals("y")){
                System.out.println("Nouveau prix conduit :");
                prixConduit = sc.nextDouble();
            }

            System.out.println("Modifier nb heures code ? (y/n)");
            if(sc.next().equals("y")){
                System.out.println("Nouveau nb heures code :");
                nbHcode = sc.nextInt();
            }

            System.out.println("Modifier nb heures conduite ? (y/n)");
            if(sc.next().equals("y")){
                System.out.println("Nouveau nb heures conduite :");
                nbHConduit = sc.nextInt();
            }

            Moniteur m1 = new Moniteur(cin, nom, prenom, etat, prixCode, prixConduit, nbHcode, nbHConduit);
            moniteurControllers.suppressionMoniteur(cin);
            moniteurControllers.ajoutMoniteur(m1);
            System.out.println("Modification effectuée avec succès.");

        } else {
            System.out.println("Moniteur introuvable.");
        }

    }

    // ----------------------- SUPPRESSION -----------------------
    public void suppressionMoniteur(){
        System.out.println("-----Supprimer Moniteur-----");
        Scanner sc = new Scanner(System.in);

        System.out.println("CIN :");
        int cin = sc.nextInt();

        boolean v = moniteurControllers.suppressionMoniteur(cin);

        if(v)
            System.out.println("Moniteur supprimé.");
        else
            System.out.println("Moniteur introuvable.");

    }

    // ----------------------- RECHERCHE -----------------------
    public void rechercheMoniteur(){
        System.out.println("-----Rechercher Moniteur-----");
        Scanner sc = new Scanner(System.in);
        System.out.println("CIN :");
        int cin = sc.nextInt();

        Moniteur m = moniteurControllers.rechercheMoniteur(cin);

        if(m != null)
            System.out.println(m.toString());
        else
            System.out.println("Moniteur introuvable.");

    }

}