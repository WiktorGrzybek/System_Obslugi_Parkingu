public class Bus extends Vehicle {
    public Bus(String registrationNumber) {
        super(registrationNumber);
    }

    @Override
    public int getSize() {
        return 2;
    }

    @Override
    public String getType() {
        return "Bus";
    }
}
