package Repositories;

import Models.Moniteur;
import Models.Moniteur;

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

    public void save(Moniteur m) {
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

            System.out.println("✅ Moniteur saved successfully to JSON file!");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Error while saving moniteur!");
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

    public void modifierMoniteur(Moniteur c) {
        // 1. Load all moniteures from the file
        List<Moniteur> allMoniteurs = getAllMoniteurs();
        boolean found = false;

        // 2. Find the moniteure in the list and replace it
        for (int i = 0; i < allMoniteurs.size(); i++) {
            if (allMoniteurs.get(i).getCin() == c.getCin()) {
                // Replace the old object with the modified one
                allMoniteurs.set(i, c);
                found = true;
                break;
            }
        }

        // 3. Save the updated list back to the file
        if (found) {
            try {
                mapper.writerWithDefaultPrettyPrinter()
                        .writeValue(new File(FILE_PATH), allMoniteurs);
                System.out.println("✅ Moniteur modified successfully!");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("❌ Error while saving modification!");
            }
        } else {
            System.out.println("❌ Moniteur to modify not found in file.");
        }
    }
}