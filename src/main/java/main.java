import Controllers.CandidatController;
import Controllers.MoniteurController;
import Controllers.SeanceController;
import Controllers.VehiculeController;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Menu();
    }

    public static void Menu() {
        System.out.println("========================================");
        System.out.println("           🚗 DRIVING SCHOOL APP         ");
        System.out.println("========================================");
        System.out.println("[1] ▶ Manage Candidats");
        System.out.println("[2] ▶ Manage Moniteurs");
        System.out.println("[3] ▶ Manage Vehicules");
        System.out.println("[4] ▶ Manage Seances");
        System.out.println("[0] ▶ Exit");
        System.out.println("----------------------------------------");
        System.out.print("👉 Your choice: ");



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
                System.out.println("👋 Exiting the app. Goodbye!");
                return;
            default:
                System.out.println("❌ Invalid choice, please try again.");
        }
        Menu();
    }
}