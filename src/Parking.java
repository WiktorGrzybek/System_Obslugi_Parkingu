import java.util.*;

public class Parking {
    private int rows;
    private int cols;
    private ParkingSpot[][] spots;
    private List<Vehicle> vehicles;

    public Parking(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        spots = new ParkingSpot[rows][cols];
        vehicles = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                spots[i][j] = new ParkingSpot(i, j);
            }
        }
    }

    public boolean addVehicle(Vehicle vehicle) throws ParkingException {
        if (vehicles.stream().anyMatch((v) -> v.getRegistrationNumber().equals(vehicle.getRegistrationNumber())))
            throw new ParkingException("Rejestracja samochodowa już istnieje");

        for (int startRow = 0;startRow < rows;startRow++)
        {
            for (int startCol = 0;startCol < cols;startCol++)
            {
                if (canPark(vehicle, startRow, startCol) && !(vehicle instanceof Bus))
                {
                    List<int[]> occupiedSpots = new ArrayList<>();
                    for (int i = 0; i < vehicle.getSize(); i++) {
                        int row = startRow + (i / cols);
                        int col = startCol + (i % cols);
                        spots[row][col].occupy(vehicle);
                        occupiedSpots.add(new int[]{row, col});
                    }
                    vehicle.occupiedSpots.addAll(occupiedSpots);
                    vehicles.add(vehicle);
                    return true;
                }
                else if (canPark(vehicle, startRow, startCol)
                        && canPark(vehicle, startRow + 1, startCol)
                        && (startRow + 1) % 2 == 0)
                {
                    List<int[]> occupiedSpots = new ArrayList<>();
                    for (int i = 0; i < vehicle.getSize(); i++) {
                        int row = startRow + (i / cols);
                        int col = startCol + (i % cols);
                        spots[row][col].occupy(vehicle);
                        occupiedSpots.add(new int[]{row, col});
                    }
                    for (int i = 0; i < vehicle.getSize(); i++) {
                        int row = startRow + 1 + (i / cols);
                        int col = startCol + (i % cols);
                        spots[row][col].occupy(vehicle);
                        occupiedSpots.add(new int[]{row, col});
                    }
                    vehicle.occupiedSpots.addAll(occupiedSpots);
                    vehicles.add(vehicle);
                    return true;
                }
            }
        }
        throw new ParkingException("No space available to park the vehicle");
    }

    public boolean removeVehicle(String registrationNumber) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getRegistrationNumber().equals(registrationNumber)) {
                for (int[] spot : vehicle.getOccupiedSpots()) {
                    spots[spot[0]][spot[1]].vacate();
                }
                vehicles.remove(vehicle);
                return true;
            }
        }
        return false;
    }

    private boolean canPark(Vehicle vehicle, int startRow, int startCol) {
        for (int i = 0; i < vehicle.getSize(); i++) {
            int row = startRow + (i / cols);
            int col = startCol + (i % cols);
            if (row >= rows || col >= cols || spots[row][col].isOccupied()) {
                return false;
            }
        }
        return true;
    }

    public String displayParking() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0, line=1; i < rows; i++) {
            if (i == 1 || line == i){
                line += 2;
                sb.append(DrawLine());
            }
            for (int j = 0; j < cols; j++) {

                if (spots[i][j].isOccupied()) {
                    sb.append("[" + spots[i][j].getVehicle().getType().charAt(0) + "]");
                } else {
                    sb.append("[ ]");
                }
            }
            sb.append('\n');
        }

        return sb.toString();
    }

    private String DrawLine(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cols; i++)
            sb.append("--");
        sb.append('\n');
        return sb.toString();
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public ParkingSpot getSpot(int i, int j) {
        return spots[i][j];
    }
}
