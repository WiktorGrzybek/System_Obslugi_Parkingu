public class Car extends Vehicle {
    public Car(String registrationNumber) {
        super(registrationNumber);
    }

    @Override
    public int getSize() {
        return 2;
    }

    @Override
    public String getType() {
        return "Car";
    }
}
