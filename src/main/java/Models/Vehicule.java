package Models;

import java.time.LocalDate;

public class Vehicule {
    private String type;
    private int numMat;
    private LocalDate date;
    private double kmTotale;
    private double kmReste;

    public Vehicule() {}
    public Vehicule(int numMat,String type,  double kmTotale, double kmReste) {
        this.type = type;
        this.numMat = numMat;
        this.date = LocalDate.now();
        this.kmTotale = kmTotale;
        this.kmReste = kmReste;
    }

    // Constructor to preserve date during modification
    public Vehicule(int numMat,String type, LocalDate date, double kmTotale, double kmReste) {
        this.type = type;
        this.numMat = numMat;
        this.date = date;
        this.kmTotale = kmTotale;
        this.kmReste = kmReste;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumMat() {
        return numMat;
    }

    public void setNumMat(int numMat) {
        this.numMat = numMat;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getKmTotale() {
        return kmTotale;
    }

    public void setKmTotale(double kmTotale) {
        this.kmTotale = kmTotale;
    }

    public double getKmReste() {
        return kmReste;
    }

    public void setKmReste(double kmReste) {
        this.kmReste = kmReste;
    }

    @Override
    public String toString() {
        return "Vehicule{" +
                "type='" + type + '\'' +
                ", numMat=" + numMat +
                ", date=" + date +
                ", kmTotale=" + kmTotale +
                ", kmReste=" + kmReste +
                '}';
    }
}