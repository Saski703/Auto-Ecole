package Repositories;

import Models.Candidat;
// Import this TypeReference to help Jackson read the List
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CandidatRepositories {
    private static final String FILE_PATH = "candidats.json";
    private final ObjectMapper mapper;


    public CandidatRepositories() {
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
    }

    public void ajoutCandidat(Candidat c) {
        List<Candidat> candidats; // Declare list
        File file = new File(FILE_PATH);

        try {
            // 1. READ existing list from JSON
            if (file.exists() && file.length() > 0) {
                // If file has content, read it into the list
                candidats = mapper.readValue(file, new TypeReference<List<Candidat>>() {});
            } else {
                // Otherwise, start with a new empty list
                candidats = new ArrayList<>();
            }

            // 2. ADD the new candidat to the list
            candidats.add(c);

            // 3. WRITE the full, updated list back to the file
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(file, candidats);

            System.out.println("✅ Candidat saved successfully to JSON file!");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Error while saving candidat!");
        }
    }

    public List<Candidat> getAllCandidats() {
        File file = new File(FILE_PATH);
        List<Candidat> candidats = new ArrayList<>();

        try {
            if (file.exists() && file.length() > 0) {
                candidats = mapper.readValue(file, new TypeReference<List<Candidat>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Error while reading candidats!");
        }

        return candidats;
    }
    public Candidat recherchreCondidat(int numCin) {
        List<Candidat> allCandidats = getAllCandidats();

        for (Candidat candidat : allCandidats) {
            if (candidat.getNumCin() == numCin ) {
                return candidat;
            }
        }

        return null;
    }


    public boolean suppressionCandidat(int numCin) {
        List<Candidat> allCandidats = getAllCandidats();

        boolean removed = allCandidats.removeIf(candidat -> candidat.getNumCin() == numCin);

        File file = new File(FILE_PATH);

        if(removed) {
            try {
                mapper.writerWithDefaultPrettyPrinter().writeValue(file, allCandidats);
                System.out.println("✅ Candidat deleted successfully!");

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("❌ Error while deleting candidat!");
            }
        }
        return removed;
    }
    public void modifierCandidat(Candidat c) {
        // 1. Load all candidates from the file
        List<Candidat> allCandidats = getAllCandidats();
        boolean found = false;

        // 2. Find the candidate in the list and replace it
        for (int i = 0; i < allCandidats.size(); i++) {
            if (allCandidats.get(i).getNumCin() == c.getNumCin()) {
                // Replace the old object with the modified one
                allCandidats.set(i, c);
                found = true;
                break;
            }
        }

        // 3. Save the updated list back to the file
        if (found) {
            try {
                mapper.writerWithDefaultPrettyPrinter()
                        .writeValue(new File(FILE_PATH), allCandidats);
                System.out.println("✅ Candidat modified successfully!");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("❌ Error while saving modification!");
            }
        } else {
            System.out.println("❌ Candidat to modify not found in file.");
        }
    }

}