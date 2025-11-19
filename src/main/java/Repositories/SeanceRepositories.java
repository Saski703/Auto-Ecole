package Repositories;

import Models.Seance;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SeanceRepositories {
    private static final String FILE_PATH = "seance.json";
    private final ObjectMapper mapper;


    public SeanceRepositories() {
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
    }

    public void save(Seance s) {
        List<Seance> seances;
        File file = new File(FILE_PATH);

        try {
            if (file.exists() && file.length() > 0) {
                seances = mapper.readValue(file, new TypeReference<List<Seance>>() {});
            } else {

                seances = new ArrayList<>();
            }

            seances.add(s);

            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(file, seances);

            System.out.println("✅ Seance saved successfully to JSON file!");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Error while saving candidat!");
        }
    }

    public List<Seance> getAllSeances() {
        File file = new File(FILE_PATH);
        List<Seance> seances = new ArrayList<>();

        try {
            if (file.exists() && file.length() > 0) {
                seances = mapper.readValue(file, new TypeReference<List<Seance>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Error while reading seances!");
        }

        return seances;
    }

    public boolean suppressionSeance(int num) {
        List<Seance> allSeances = getAllSeances();

        boolean removed = allSeances.removeIf(seance -> seance.getNum() == num);

        File file = new File(FILE_PATH);

        if(removed) {
            try {
                mapper.writerWithDefaultPrettyPrinter().writeValue(file, allSeances);
                System.out.println("✅ Seance deleted successfully!");

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("❌ Error while deleting seance!");
            }
        }
        return removed;
    }
}