package Models;

import java.time.LocalDate;


public class Candidat {
    private int numCin;
    private String nom;
    private String prenom;
    private LocalDate date;
    private String adresse;
    private int telephone;
    private String typePermis;

    public Candidat() {
    }
    public Candidat(int numCin, String nom, String prenom, String adresse,int telephone, String typePermis) {
        this.numCin = numCin;
        this.nom = nom;
        this.prenom = prenom;
        this.date = LocalDate.now();
        this.adresse = adresse;
        this.telephone = telephone;
        this.typePermis = typePermis;

    }

    public Candidat(int numCin, String nom, String prenom, LocalDate date, String adresse,int telephone, String typePermis) {
        this.numCin = numCin;
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
        this.adresse = adresse;
        this.telephone = telephone;
        this.typePermis = typePermis;

    }

    public int getNumCin() {
        return numCin;
    }

    public void setNumCin(int numCin) {
        this.numCin = numCin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getTypePermis() {
        return typePermis;
    }

    public void setTypePermis(String typePermis) {
        this.typePermis = typePermis;
    }

    @Override
    public String toString() {
        return """
           Candidat Details
           ----------------
           CIN         : %s
           Nom Complet : %s %s
           Telephone   : %s
           Adresse     : %s
           Date Naiss. : %s
           Type Permis : %s
           """.formatted(
                numCin,
                (nom != null ? nom.toUpperCase() : ""), // Modern touch: Uppercase Last Name
                (prenom != null ? prenom : ""),
                telephone,
                (adresse != null ? adresse : "N/A"),
                (date != null ? date : "N/A"),
                (typePermis != null ? typePermis : "N/A")
        );
    }

}
