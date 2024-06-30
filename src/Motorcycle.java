public class Motorcycle extends Vehicle {
    public Motorcycle(String registrationNumber) {
        super(registrationNumber);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getType() {
        return "Motorcycle";
    }
}
