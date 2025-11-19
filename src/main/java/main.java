import Controllers.CandidatController;
import Controllers.MoniteurController;
import Controllers.SeanceController;
import Controllers.VehiculeController;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
            Menu();
    }


    public static void Menu(){
        System.out.println("Menu Principal");
        System.out.println("1. Candidat");
        System.out.println("2. Moniteur");
        System.out.println("3. Vehicule");
        System.out.println("4. Seance");
        System.out.println("0. Exit");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                CandidatController candidatController = new CandidatController();
                candidatController.init();
                break;
            case 2:
                MoniteurController moniteurController = new MoniteurController();
                moniteurController.init();
                break;
            case 3:
                VehiculeController vehiculeController = new VehiculeController();
                vehiculeController.init();
                break;
            case 4:
                SeanceController seanceController = new SeanceController();
                seanceController.init();
                break;
            case 0:
                System.out.println("exit");
                return;

            default:
                System.out.println("Invalid choice");
        }
        Menu();
    }
}