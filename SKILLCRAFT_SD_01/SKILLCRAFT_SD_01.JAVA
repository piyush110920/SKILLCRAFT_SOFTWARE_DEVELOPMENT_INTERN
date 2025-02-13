import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SKILLCRAFT_SD_01 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Temperature Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 250);
        frame.setLayout(new GridLayout(5, 1));
        frame.setResizable(false);
        JLabel labelTemperature = new JLabel("Enter Temperature:");
        labelTemperature.setFont(new Font("Arial", Font.BOLD, 14)); 
        JTextField textFieldTemperature = new JTextField();
        textFieldTemperature.setPreferredSize(new Dimension(100, 25)); 
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(labelTemperature);
        inputPanel.add(textFieldTemperature);
        frame.add(inputPanel);
        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
        JComboBox<String> unitDropdown = new JComboBox<>(units);
        JPanel dropdownPanel = new JPanel(new FlowLayout());
        dropdownPanel.add(new JLabel("Select Unit:"));
        dropdownPanel.add(unitDropdown);
        frame.add(dropdownPanel);
        JButton convertButton = new JButton("Convert");
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(convertButton);
        frame.add(buttonPanel);
        JLabel resultLabel = new JLabel("");
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(resultLabel);
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double temperature = Double.parseDouble(textFieldTemperature.getText());
                    String selectedUnit = (String) unitDropdown.getSelectedItem();
                    String result = "";
                    if (selectedUnit.equals("Celsius")) {
                        double fahrenheit = (temperature * 9 / 5) + 32;
                        double kelvin = temperature + 273.15;
                        result = String.format("%.2f °C = %.2f °F = %.2f K", temperature, fahrenheit, kelvin);
                    } else if (selectedUnit.equals("Fahrenheit")) {
                        double celsius = (temperature - 32) * 5 / 9;
                        double kelvin = celsius + 273.15;
                        result = String.format("%.2f °F = %.2f °C = %.2f K", temperature, celsius, kelvin);
                    } else {
                        double celsius = temperature - 273.15;
                        double fahrenheit = (celsius * 9 / 5) + 32;
                        result = String.format("%.2f K = %.2f °C = %.2f °F", temperature, celsius, fahrenheit);
                    }
                    resultLabel.setText(result);
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Please enter a valid number.");
                }
            }
        });
        frame.setVisible(true);
    }
}