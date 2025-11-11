package Ui;

import Controllers.CandidatControllers;
import Models.Candidat;

import java.time.LocalDate;
import java.util.Scanner;

public class CondidatUi {

    CandidatControllers candidatControllers = new CandidatControllers();

    public void Menu(){
        System.out.println("-----Menu-----");
        System.out.println("1.Ajout Condidat");
        System.out.println("2.Modifier Condidat");
        System.out.println("3.Supprimer Condidat");
        System.out.println("4.Rechercher Condidat");
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
            default:
                System.out.println("Invalid choice");
        }
    }
    public void ajoutCandidat(){
        System.out.println("-----Ajout Condidat-----");
        Scanner sc = new Scanner(System.in);
        System.out.println("Num Cin");
        int numCin = sc.nextInt();
        System.out.println("Nom :");
        String nom = sc.next();
        System.out.println("Prenom :");
        String prenom = sc.next();
        System.out.println("Date :");
        String d = sc.next();
        LocalDate date = LocalDate.parse(d);//  2025/10/10
        System.out.println("Adresse :");
        String adresse = sc.next();
        System.out.println("telephone :");
        int telephone = sc.nextInt();
        System.out.println("type permis :");
        String permis = sc.next();

        Candidat c = new Candidat(numCin, nom, prenom, date, adresse, telephone, permis);
        candidatControllers.ajoutCandidat(c);
    }

    public void modifierCandidat(){

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
        Candidat c = candidatControllers.recherchreCandidat(numCin);
        if(c!=null){
            System.out.println(c.toString());
        }else {
            System.out.println("Candidat inexistant");
        }

    }

}
