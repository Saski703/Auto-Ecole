package Models;

import java.time.LocalDate;
import java.time.LocalTime;

public class ExamenCode extends Examen {

    public ExamenCode() { }

    public ExamenCode(int num, LocalDate date, LocalTime heure, Moniteur moniteur, Candidat candidat, int result,double prix) {
        super(num, date, heure, moniteur, candidat, prix, result);
    }

    @Override
    public String getType() {
        return "EXAMEN_CODE";
    }

    @Override
    public String toString() {
        return "🎓 [EXAMEN CODE]     " + super.toString();
    }
}