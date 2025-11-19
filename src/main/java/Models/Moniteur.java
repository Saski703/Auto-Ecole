package Models;

public class Moniteur {
    private int cin;
    private String nom;
    private String prenom;
    private boolean etat;
    private double prixCode;  //salaire d'une seance de code
    private double prixConduit;
    private int nbHeureCode;
    private int nbHeureConduit;

    // --------------------Constructeurs--------------------
    public Moniteur() {}

    public Moniteur(int cin, String nom, String prenom, boolean etat, double prixCode, double prixConduit, int nbHeureCode, int nbHeureConduit) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.etat = etat;
        this.prixCode = prixCode;
        this.prixConduit = prixConduit;
        this.nbHeureCode = nbHeureCode;
        this.nbHeureConduit = nbHeureConduit;
    }
    public Moniteur(int cin, String nom, String prenom, boolean etat, double prixCode, double prixConduit) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.etat = etat;
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

    public boolean getEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
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

    public int getNbHeureCode() {
        return nbHeureCode;
    }

    public void setNbHeureCode(int nbHeureCode) {
        this.nbHeureCode = nbHeureCode;
    }

    public int getNbHeureConduit() {
        return nbHeureConduit;
    }

    public void setNbHeureConduit(int nbHeureConduit) {
        this.nbHeureConduit = nbHeureConduit;
    }

    //todo : salaire??
    /*
    public double salaire() {
        return (nbHeureCode * prixCode) + (nbHeureConduit * prixConduit);
    }*/

    // --------------------toString--------------------
    @Override
    public String toString() {
        return "Moniteur{" +
                "nom='" + nom + '\'' +
                ", etat=" + (etat ? "Actif" : "Inactif") + // More readable output
                ", prixCode=" + prixCode +
                ", prixConduit=" + prixConduit +
                ", nbHeureCode=" + nbHeureCode +
                ", nbHeureConduit=" + nbHeureConduit +/*
                ", salaire=" + salaire() +*/
                '}';
    }
}