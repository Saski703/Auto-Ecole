package Repositories;

import Models.Vehicule;
// Import this TypeReference to help Jackson read the List
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VehiculeRepositories {
    private static final String FILE_PATH = "vehicules.json";
    private final ObjectMapper mapper;

    public VehiculeRepositories() {
        this.mapper = new ObjectMapper();
        // Register the JavaTimeModule to correctly handle LocalDate
        this.mapper.registerModule(new JavaTimeModule());
    }

    /**
     * Reads the list of vehicles, adds a new one, and saves the updated list back to JSON.
     * @param v The new Vehicule to add.
     */
    public void ajoutVehicule(Vehicule v) {
        List<Vehicule> vehicules; // Declare list
        File file = new File(FILE_PATH);

        try {
            // 1. READ existing list from JSON
            if (file.exists() && file.length() > 0) {
                // If file has content, read it into the list
                vehicules = mapper.readValue(file, new TypeReference<List<Vehicule>>() {});
            } else {
                // Otherwise, start with a new empty list
                vehicules = new ArrayList<>();
            }

            // 2. ADD the new vehicule to the list
            vehicules.add(v);

            // 3. WRITE the full, updated list back to the file
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(file, vehicules);

            System.out.println("✅ Vehicule saved successfully to JSON file!");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Error while saving vehicule!");
        }
    }

    /**
     * Reads and returns all vehicles from the JSON file.
     * @return A list of all vehicles, or an empty list if the file is empty/missing or an error occurs.
     */
    public List<Vehicule> getAllVehicules() {
        File file = new File(FILE_PATH);
        List<Vehicule> vehicules = new ArrayList<>();

        try {
            if (file.exists() && file.length() > 0) {
                vehicules = mapper.readValue(file, new TypeReference<List<Vehicule>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Error while reading vehicules!");
        }

        return vehicules;
    }

    /**
     * Searches for a vehicle by its registration number.
     * @param numMat The registration number (numMat) to search for.
     * @return The found Vehicule, or null if not found.
     */
    public Vehicule rechercherVehicule(int numMat) {
        List<Vehicule> allVehicules = getAllVehicules();

        for (Vehicule vehicule : allVehicules) {
            if (vehicule.getNumMat() == numMat) {
                return vehicule;
            }
        }

        return null;
    }

    /**
     * Deletes a vehicle by its registration number and updates the JSON file.
     * @param numMat The registration number (numMat) of the vehicle to delete.
     * @return true if the vehicle was found and deleted, false otherwise.
     */
    public boolean suppressionVehicule(int numMat) {
        List<Vehicule> allVehicules = getAllVehicules();

        // Use removeIf to find and remove the matching vehicule
        boolean removed = allVehicules.removeIf(vehicule -> vehicule.getNumMat() == numMat);

        File file = new File(FILE_PATH);

        if (removed) {
            try {
                // If removed, write the modified list back to the file
                mapper.writerWithDefaultPrettyPrinter().writeValue(file, allVehicules);
                System.out.println("✅ Vehicule deleted successfully!");

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("❌ Error while updating file after deletion!");
            }
        }
        return removed;
    }
}