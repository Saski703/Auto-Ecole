package Models;

import java.time.LocalDate;


public class Candidat {
    private int numCin;
    private String nom;
    private String prenom;
    private LocalDate date;
    private String adresse;
    private String telephone;
    private String typePermis;
    Candidat(int numCin, String nom, String prenom, String adresse, LocalDate date, String telephone, String typePermis) {
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
