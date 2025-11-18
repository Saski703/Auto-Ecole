package Models;

import java.time.LocalDate;

public class Maintenance {
    private String description;       // Ex: "Changement plaquettes freins"
    private LocalDate date;           // Date de la réparation
    //private double montant;  Coût (pour le module comptabilité F5)
    //private String cheminPreuve;      // Chemin du fichier scanné (ex: "C:/scans/facture1.jpg")

    public Maintenance() {}

    public Maintenance(String description, LocalDate date) {
        this.description = description;
        this.date = date;
    }

    // Getters & Setters
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }


    @Override
    public String toString() {
        return date + " : " + description ;
    }
}