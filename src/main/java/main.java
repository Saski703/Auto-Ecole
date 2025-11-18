import Controllers.CandidatControllers;
import Controllers.MoniteurControllers;
import Controllers.VehiculeControllers;
import Models.Candidat;

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
        System.out.println("0. Exit");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                CandidatControllers candidatControllers = new CandidatControllers();
                candidatControllers.init();
                break;
            case 2:
                MoniteurControllers moniteurControllers = new MoniteurControllers();
                moniteurControllers.init();
                break;
            case 3:
                VehiculeControllers vehiculeControllers = new VehiculeControllers();
                vehiculeControllers.init();
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