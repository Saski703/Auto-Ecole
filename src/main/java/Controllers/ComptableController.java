package Controllers;
import Services.ComptableServices;
import Ui.ComptableUi;

public class ComptableController {

    static ComptableServices comptableServices = new ComptableServices();
    public void init(){
        ComptableUi comptableUi = new ComptableUi();
        comptableUi.Menu();
    }

    public static void afficherBilan(int mois, int annee) {
        comptableServices.afficherBilan(mois, annee);
    }

}
