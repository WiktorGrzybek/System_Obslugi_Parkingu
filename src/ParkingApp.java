import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParkingApp {
    private Parking parking;
    private JTextArea displayArea;
    private JTextArea displayAreaInfo;

    public ParkingApp() {
        parking = new Parking(10, 10);
        initializeUI();
    }

    private void initializeUI() {
        JFrame frame = new JFrame("Parking System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        displayArea = new JTextArea();
        displayArea.setEditable(false);

        displayAreaInfo = new JTextArea();
        displayAreaInfo.setEditable(false);
        panel.add(new JScrollPane(displayArea));
        panel.add(new JScrollPane(displayAreaInfo));

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(3, 2));

        JLabel regLabel = new JLabel("Registration Number:");
        JTextField regField = new JTextField();
        controlPanel.add(regLabel);
        controlPanel.add(regField);

        JLabel typeLabel = new JLabel("Vehicle Type:");
        String[] vehicleTypes = {"Motorcycle", "Car", "Bus"};
        JComboBox<String> typeCombo = new JComboBox<>(vehicleTypes);
        controlPanel.add(typeLabel);
        controlPanel.add(typeCombo);

        JButton addButton = new JButton("Add Vehicle");
        controlPanel.add(addButton);

        JButton removeButton = new JButton("Remove Vehicle");
        controlPanel.add(removeButton);

        JButton loadLogsButton = new JButton("Wczytaj dane");
        controlPanel.add(loadLogsButton);

        panel.add(controlPanel);
        frame.add(panel);
        var connection = new DbConnection();
        connection.Connect();

        loadLogsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var text = regField.getText();

                if (text == null || text.equals("")) {
                    displayAreaInfo.setText(connection.LoadLogs());
                }
                else {
                    displayAreaInfo.setText(connection.LoadLogs(text));
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String registrationNumber = regField.getText();
                String vehicleType = (String) typeCombo.getSelectedItem();
                if (!ParkingValidator.validateRegistrationNumber(registrationNumber)) {
                    JOptionPane.showMessageDialog(frame, "Invalid registration number");
                    return;
                }
                try {
                    Vehicle vehicle;
                    switch (vehicleType) {
                        case "Motorcycle":
                            vehicle = new Motorcycle(registrationNumber);
                            break;
                        case "Car":
                            vehicle = new Car(registrationNumber);
                            break;
                        case "Bus":
                            vehicle = new Bus(registrationNumber);
                            break;
                        default:
                            throw new IllegalArgumentException("Unknown vehicle type");
                    }
                    if (parking.addVehicle(vehicle)) {
                        connection.AddLog(vehicle.getRegistrationNumber(), vehicle.getType(),vehicle.GetRzad(), true );
                        displayParking();
                        displayParkingInfo();
                    }
                } catch (ParkingException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String registrationNumber = regField.getText();
                var vehicleStream = parking.getVehicles().stream().filter((v) -> v.getRegistrationNumber().equals(registrationNumber));
                Vehicle vehicle = null;
                try {
                    vehicle = vehicleStream.findFirst().get();
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(frame, "Vehicle not found");
                    return;
                }

                connection.AddLog(vehicle.getRegistrationNumber(), vehicle.getType(),vehicle.GetRzad(), false );
                parking.removeVehicle(registrationNumber);

                displayParking();
                displayParkingInfo();
            }
        });

        displayParking();
        frame.setVisible(true);
    }

    private void displayParking() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parking.getRows(); i++) {
            for (int j = 0; j < parking.getCols(); j++) {
                ParkingSpot spot = parking.getSpot(i, j);
                if (spot.isOccupied()) {
                    sb.append("[").append(spot.getVehicle().getType().charAt(0)).append("]");
                } else {
                    sb.append("[ ]");
                }
            }
            sb.append("\n");
        }
        displayArea.setText(parking.displayParking());
    }

    private void displayParkingInfo() {
        StringBuilder sb = new StringBuilder();
        var vechicals = parking.getVehicles();

        for (var vechicle: vechicals) {
            sb.append(vechicle.toString());
            sb.append('\n');
        }

        displayAreaInfo.setText(sb.toString());
    }

    public static void main(String[] args) {
        new ParkingApp();
    }
}
