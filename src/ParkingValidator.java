public class ParkingValidator {
    public static boolean validateRegistrationNumber(String registrationNumber) {
        return registrationNumber != null && !registrationNumber.trim().isEmpty();
    }

    public static boolean validatePosition(int row, int col, int maxRows, int maxCols) {
        return row >= 0 && row < maxRows && col >= 0 && col < maxCols;
    }
}
