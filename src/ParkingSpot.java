public class ParkingSpot {
    private int row;
    private int col;
    private Vehicle vehicle;

    public ParkingSpot(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isOccupied() {
        return vehicle != null;
    }

    public void occupy(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void vacate() {
        this.vehicle = null;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
