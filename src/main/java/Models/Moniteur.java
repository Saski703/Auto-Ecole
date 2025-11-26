package Models;

public class Moniteur {
    private int cin;
    private String nom;
    private String prenom;
    private double prixCode;  //salaire d'une seance de code
    private double prixConduit;

    // --------------------Constructeurs--------------------
    public Moniteur() {}

    public Moniteur(int cin, String nom, String prenom, double prixCode, double prixConduit) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.prixCode = prixCode;
        this.prixConduit = prixConduit;
    }
    // -------------------- Getter and Setters --------------------
    public int getCin() {
        return this.cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
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

    public double getPrixCode() {
        return prixCode;
    }

    public void setPrixCode(double prixCode) {
        this.prixCode = prixCode;
    }

    public double getPrixConduit() {
        return prixConduit;
    }

    public void setPrixConduit(double prixConduit) {
        this.prixConduit = prixConduit;
    }


    // --------------------toString--------------------
    @Override
    public String toString() {
        return "Moniteur{" +
                "cin=" + cin +
                ", nom='" + nom + '\'' +
                ", prixCode=" + prixCode +
                ", prixConduit=" + prixConduit +
                '}';
    }
}