package Controllers;
import Models.Vehicule;
import Services.VehiculeServices;
import Ui.CandidatUi;
import Ui.VehiculeUi;

public class VehiculeControllers {
    VehiculeServices vehiculeServices = new VehiculeServices();
    public void init(){
        VehiculeUi vehiculeUi = new VehiculeUi();
        vehiculeUi.Menu();

    }

    public void ajoutVehicule(Vehicule c) {
        vehiculeServices.ajoutVehicule(c);
    }

    public Vehicule rechercherVehicule(int numMat) {
        return vehiculeServices.rechercherVehicule(numMat);
    }

    public boolean suppressionVehicule(int numMat) {
        return vehiculeServices.suppressionVehicule(numMat);
    }

}