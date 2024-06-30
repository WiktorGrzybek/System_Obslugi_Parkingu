import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Vehicle {
    protected String registrationNumber;
    protected List<int[]> occupiedSpots;

    public Vehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
        this.occupiedSpots = new ArrayList<>();
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public List<int[]> getOccupiedSpots() {
        return occupiedSpots;
    }

    public abstract int getSize();
    public abstract String getType();

    public String GetRzad(){
        var spots = getOccupiedSpots();
        StringBuilder sb = new StringBuilder();
        sb.append("Miejsca: ");

        for (var i = 0; i < spots.size(); i++){
            if (i % 2 == 0)
                sb.append("rząd " + Arrays.toString(spots.get(i)) + " ");
            else
                sb.append("Kolumna " + Arrays.toString(spots.get(i)) + " ");

        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return getType() + " [Nr: " + registrationNumber + "] " + GetRzad();
    }
}

