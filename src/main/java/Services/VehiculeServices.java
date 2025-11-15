package Services;

import Models.Vehicule;
import Repositories.VehiculeRepositories;

public class VehiculeServices {
    VehiculeRepositories vehiculeRepos = new VehiculeRepositories();
    public void ajoutVehicule(Vehicule v) {
        vehiculeRepos.ajoutVehicule(v);
    }
    public Vehicule rechercherVehicule(int numMat) {
        return vehiculeRepos.rechercherVehicule(numMat);
    }

    public boolean suppressionVehicule(int numMat) {
        return vehiculeRepos.suppressionVehicule(numMat);
    }
}
