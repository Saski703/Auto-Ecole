package Ui;

import Controllers.CandidatControllers;
import Models.Candidat;

import java.time.LocalDate;
import java.util.Scanner;

public class CandidatUi {

    CandidatControllers candidatControllers = new CandidatControllers();

    public void Menu(){
        System.out.println("-----Menu-----");
        System.out.println("1.Ajout Condidat");
        System.out.println("2.Modifier Condidat");
        System.out.println("3.Supprimer Condidat");
        System.out.println("4.Rechercher Condidat");
        System.out.println("5.afficher Les Condidats");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch(choice){
            case 1:
                ajoutCandidat();
                break;
            case 2:
                modifierCandidat();
                break;
            case 3:
                suppressionCandidat();
                break;
            case 4:
                recherchreCandidat();
                break;
            case 5:
                afficherLesCandidats();
                break;
            default:
                System.out.println("Invalid choice");
        }
        Menu();
    }
    public void ajoutCandidat(){
        System.out.println("-----Ajout Condidat-----");
        Scanner sc = new Scanner(System.in);
        System.out.println("Num Cin");
        int numCin = sc.nextInt();
        if(candidatControllers.rechercheCandidat(numCin) != null) {
            System.out.println("Un Candidat with this NumCin already exists.");
            return;
        }
        System.out.println("Nom :");
        String nom = sc.next();
        System.out.println("Prenom :");
        String prenom = sc.next();
        System.out.println("Adresse :");
        String adresse = sc.next();
        System.out.println("telephone :");
        int telephone = sc.nextInt();
        System.out.println("type permis :");
        String permis = sc.next();

        Candidat c = new Candidat(numCin, nom, prenom, adresse, telephone, permis);
        candidatControllers.ajoutCandidat(c);
    }

    public void modifierCandidat(){
        System.out.println("-----Modifier Condidat-----");
        Scanner sc = new Scanner(System.in);
        System.out.println("Num Cin :");
        int numCin = sc.nextInt();
        Candidat c = candidatControllers.rechercheCandidat(numCin);


        if(c!=null){

            String name = c.getNom();
            String prenom = c.getPrenom();
            String adresse = c.getAdresse();
            int telephone = c.getTelephone();
            String permis = c.getTypePermis();
            LocalDate date = c.getDate();

            System.out.println(c.toString());
            System.out.println("do you want to modifier 'name'? (y/n)");
            String response = sc.next();
            if(response.equals("y")){
                System.out.println("new name: ");
                name = sc.next();
            }

            System.out.println("do you want to modifier 'prenom'? (y/n)");
            response = sc.next();
            if(response.equals("y")){
                System.out.println("new prenom: ");
                prenom = sc.next();
            }
            System.out.println("do you want to modifier 'adresse'? (y/n)");
            response = sc.next();
            if(response.equals("y")){
                System.out.println("new adresse: ");
                adresse = sc.next();
            }
            System.out.println("do you want to modifier 'telephone'? (y/n)");
            response = sc.next();
            if(response.equals("y")){
                System.out.println("new telephone: ");
                telephone = sc.nextInt();
            }
            System.out.println("do you want to modifier 'permis'? (y/n)");
            response = sc.next();
            if(response.equals("y")){
                System.out.println("new permis: ");
                permis = sc.next();
            }

            Candidat c1 = new Candidat(numCin, name, prenom, date,  adresse, telephone, permis);

            candidatControllers.suppressionCandidat(c.getNumCin());
            candidatControllers.ajoutCandidat(c1);

        }else {
            System.out.println("Candidat inexistant");
        }


    }

    public void suppressionCandidat(){
        System.out.println("-----Supprimer Condidat-----");
        Scanner sc = new Scanner(System.in);
        System.out.println("Num Cin :");
        int numCin = sc.nextInt();
        boolean v =  candidatControllers.suppressionCandidat(numCin);
        if(v){
            System.out.println("Candidat supprimer");
        }else {
            System.out.println("Candidat introuvable");
        }


    }

    public void recherchreCandidat() {
        System.out.println("-----Recherche Condidat-----");
        Scanner sc = new Scanner(System.in);
        System.out.println("Num Cin :");
        int numCin = sc.nextInt();
        Candidat c = candidatControllers.rechercheCandidat(numCin);
        if(c!=null){
            System.out.println(c.toString());
        }else {
            System.out.println("Candidat inexistant");
        }

    }

    public void afficherLesCandidats() {
        System.out.println("-----Afficher les Condidat-----");
        candidatControllers.afficherLesCandidats();
    }

}
