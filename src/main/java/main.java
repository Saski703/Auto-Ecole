import Controllers.CandidatControllers;
import Controllers.VehiculeControllers;

public class main {

    public static void main(String[] args) {
        CandidatControllers condidatControllers = new CandidatControllers();
        VehiculeControllers vehiculeControllers = new VehiculeControllers();
        vehiculeControllers.init();
    }
}