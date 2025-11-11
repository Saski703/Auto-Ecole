// Import all necessary Java libraries
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate; // <-- Need this for the Candidat class

// Import all necessary Jackson libraries
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule; // <-- Need this for LocalDate

// Import the Candidat class from its package
import Models.Candidat;

public class main {

    public static void main(String[] args) {

        // --- 1. SETUP ---
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT); // Makes the JSON file readable
        mapper.registerModule(new JavaTimeModule()); // Add support for LocalDate

        File jsonFile = new File("candidats.json");

        // --- DEBUG LINE ---
        System.out.println("DEBUG: The file is being saved to this exact path:");
        System.out.println(jsonFile.getAbsolutePath());
        // ------------------

        // --- 2. LET'S SAVE SOME DATA ---
        // We must use the new Candidat constructor
        List<Candidat> listeCandidats = new ArrayList<>();
        listeCandidats.add(new Candidat(12345678, "Jean", "Martin", LocalDate.of(2025, 1, 10), "123 Rue de Paris", "55123456", "B"));
        listeCandidats.add(new Candidat(87654321, "omar", "Ben Ali", LocalDate.of(2025, 2, 20), "456 Ave Habib", "22987654", "A"));

        try {
            System.out.println("Saving candidates...");
            mapper.writeValue(jsonFile, listeCandidats);
            System.out.println("Save successful!");
        } catch (IOException e) {
            System.out.println("Save FAILED:");
            e.printStackTrace();
        }

        System.out.println("\n-----------------------------------\n");

        // --- 3. NOW LET'S LOAD THE DATA BACK ---
        try {
            System.out.println("Loading candidates...");
            List<Candidat> loadedList = mapper.readValue(jsonFile, new TypeReference<List<Candidat>>() {});

            System.out.println("Load successful! Found " + loadedList.size() + " candidates:");
            for (Candidat c : loadedList) {
                System.out.println("- " + c.getPrenom() + " " + c.getNom() + " (Permis: " + c.getTypePermis() + ")");
            }
        } catch (IOException e) {
            System.out.println("Load FAILED: File not found or other error.");
            e.printStackTrace();
        }
    }
}