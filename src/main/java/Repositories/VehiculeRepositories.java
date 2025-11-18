package Repositories;

import Models.Vehicule;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VehiculeRepositories {

    private static final String FILE_PATH = "vehicules.json";
    private final ObjectMapper mapper;

    public VehiculeRepositories() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    public void save(Vehicule v) {
        File file = new File(FILE_PATH);
        List<Vehicule> vehicules;

        try {
            vehicules = (file.exists() && file.length() > 0)
                    ? mapper.readValue(file, new TypeReference<List<Vehicule>>() {})
                    : new ArrayList<>();

            vehicules.add(v);
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, vehicules);
            System.out.println("✅ Vehicule saved successfully to JSON file!");

        } catch (IOException e) {
            System.out.println("❌ Error while saving vehicule!");
            e.printStackTrace();
        }
    }

    public List<Vehicule> getAllVehicules() {
        File file = new File(FILE_PATH);

        try {
            return (file.exists() && file.length() > 0)
                    ? mapper.readValue(file, new TypeReference<List<Vehicule>>() {})
                    : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("❌ Error while reading vehicules!");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Vehicule rechercherVehicule(String Mat) {
        for (Vehicule v : getAllVehicules()) {
            if (Objects.equals(v.getMat(), Mat)) return v;
        }
        return null;
    }

    public boolean suppressionVehicule(String Mat) {
        List<Vehicule> vehicules = getAllVehicules();
        boolean removed = vehicules.removeIf(v -> Objects.equals(v.getMat(), Mat));

        if (removed) {
            try {
                mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), vehicules);
                System.out.println("✅ Vehicule deleted successfully!");
            } catch (IOException e) {
                System.out.println("❌ Error while updating file after deletion!");
                e.printStackTrace();
            }
        }
        return removed;
    }
}
