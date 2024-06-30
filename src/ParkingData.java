import java.io.*;
import java.util.*;

public class ParkingData {
    private static final String FILE_NAME = "parking_data.txt";

    public static void saveParkingData(Parking parking) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Vehicle vehicle : parking.getVehicles()) {
                writer.write(vehicle.getType() + "," + vehicle.getRegistrationNumber());
                for (int[] spot : vehicle.getOccupiedSpots()) {
                    writer.write("," + spot[0] + "," + spot[1]);
                }
                writer.newLine();
            }
        }
    }

    public static List<Vehicle> loadParkingData() throws IOException {
        List<Vehicle> vehicles = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Vehicle vehicle;
                switch (parts[0]) {
                    case "Motorcycle":
                        vehicle = new Motorcycle(parts[1]);
                        break;
                    case "Car":
                        vehicle = new Car(parts[1]);
                        break;
                    case "Bus":
                        vehicle = new Bus(parts[1]);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown vehicle type");
                }
                for (int i = 2; i < parts.length; i += 2) {
                    vehicle.occupiedSpots.add(new int[]{Integer.parseInt(parts[i]), Integer.parseInt(parts[i + 1])});
                }
                vehicles.add(vehicle);
            }
        }
        return vehicles;
    }
}
