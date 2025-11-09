package Models;

public class Moniteur {
    private String nom;
    private boolean etat;
    private double prixCode;
    private double prixConduit;
    private int nbHeureCode;
    private int nbHeureConduit;

    public Moniteur(String nom, boolean etat, double prixCode, double prixConduit, int nbHeureCode, int nbHeureConduit) {
        this.nom = nom;
        this.etat = etat;
        this.prixCode = prixCode;
        this.prixConduit = prixConduit;
        this.nbHeureCode = nbHeureCode;
        this.nbHeureConduit = nbHeureConduit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean getEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }
    public double salaire(){
        double s=(nbHeureCode*prixCode)+(nbHeureConduit*prixCode);
        return s;
    }

}
