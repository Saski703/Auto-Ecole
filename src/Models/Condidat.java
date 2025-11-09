package Models;

import javax.lang.model.element.NestingKind;
import java.time.LocalDate;


public class Condidat {
    private int numCin;
    private String nom;
    private String prenom;
    private LocalDate date;
    private String adresse;
    private String telephone;
    private String typePermis;
    Condidat(int numCin, String nom, String prenom, String adresse, LocalDate date, String telephone, String typePermis) {
        this.numCin = numCin;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.date = date;
        this.adresse = adresse;
        this.typePermis = typePermis;

    }
}
