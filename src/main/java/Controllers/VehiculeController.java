package Controllers;
import Models.Vehicule;
import Services.VehiculeServices;
import Ui.VehiculeUi;

import java.util.List;

public class VehiculeController {
    VehiculeServices vehiculeServices = new VehiculeServices();
    public void init(){
        VehiculeUi vehiculeUi = new VehiculeUi();
        vehiculeUi.Menu();

    }

    public void ajoutVehicule(Vehicule c) {
        vehiculeServices.ajoutVehicule(c);
    }

    public Vehicule rechercherVehicule(String Mat) {
        return vehiculeServices.rechercherVehicule(Mat);
    }

    public boolean suppressionVehicule(String Mat) {
        return vehiculeServices.suppressionVehicule(Mat);
    }

    public List<String> getAlertes() {
        return vehiculeServices.getAlertes();
    }

    public void afficherVehicules(){
        vehiculeServices.afficherVehicules();
    }
}