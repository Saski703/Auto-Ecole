package Repositories;

import Models.Moniteur;
// Import this TypeReference to help Jackson read the List
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoniteurRepositories {
    private static final String FILE_PATH = "moniteur.json";
    private final ObjectMapper mapper;


    public MoniteurRepositories() {
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
    }

    public void ajoutMoniteur(Moniteur m) {
        List<Moniteur> moniteurs;
        File file = new File(FILE_PATH);

        try {
            if (file.exists() && file.length() > 0) {
                moniteurs = mapper.readValue(file, new TypeReference<List<Moniteur>>() {});
            } else {

                moniteurs = new ArrayList<>();
            }

            moniteurs.add(m);

            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(file, moniteurs);

            System.out.println("✅ Candidat saved successfully to JSON file!");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Error while saving candidat!");
        }
    }

    public List<Moniteur> getAllMoniteurs() {
        File file = new File(FILE_PATH);
        List<Moniteur> moniteurs = new ArrayList<>();

        try {
            if (file.exists() && file.length() > 0) {
                moniteurs = mapper.readValue(file, new TypeReference<List<Moniteur>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Error while reading moniteurs!");
        }

        return moniteurs;
    }
    public Moniteur rechercheMoniteur(int numCin) {
        List<Moniteur> allMoniteurs = getAllMoniteurs();

        for (Moniteur moniteur : allMoniteurs) {
            if (moniteur.getCin() == numCin) {
                return moniteur;
            }
        }
        return null;
    }


    public boolean suppressionMoniteur(int numCin) {
        List<Moniteur> allMoniteurs = getAllMoniteurs();

        boolean removed = allMoniteurs.removeIf(moniteur -> moniteur.getCin() == numCin);

        File file = new File(FILE_PATH);

        if(removed) {
            try {
                mapper.writerWithDefaultPrettyPrinter().writeValue(file, allMoniteurs);
                System.out.println("✅ Moniteur deleted successfully!");

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("❌ Error while deleting moniteur!");
            }
        }
        return removed;
    }
}